package com.example.javacourse.controllers;

import com.example.javacourse.models.*;
import com.example.javacourse.repository.CompanyRepository;
import com.example.javacourse.repository.ResponseRepository;
import com.example.javacourse.repository.UserRepository;
import com.example.javacourse.requests.UserLoginRequest;
import com.example.javacourse.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class CompanyController {

    @Autowired
    private UserRepository userRepository;  // Внедряем userRepository

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ResponseRepository responseRepository; // Внедряем ResponseRepository

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/company")
    public ResponseEntity<String> saveCompany(@RequestBody Company company) {
        try {
            // Ищем пользователя по логину
            Optional<User> userOptional = userRepository.findByLogin(company.getLogin());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User user = userOptional.get();
            System.out.println("User role: " + user.getRole()); // Логируем роль пользователя

            if (!"company".equals(user.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
            }



            company.setName_company(company.getName());
            company.setActivity(company.getActivity());
            company.setExperience(company.getExperience());
            company.setSkills(company.getSkills());
            company.setJob_title(company.getJob_title());

            // Сохраняем анкету
            companyRepository.save(company);

            return ResponseEntity.status(HttpStatus.CREATED).body("Company saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving applicant");
        }
    }
    @PostMapping("/companyTake")
    public ResponseEntity<?> getCompanyData(@RequestBody UserLoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        if (login == null || login.isEmpty()) {
            return ResponseEntity.badRequest().body("Логин не указан");
        }

        try {
            // Получаем данные компании
            List<Map<String, Object>> companyData = jdbcTemplate.queryForList(
                    "SELECT id, name_company, activity, experience, skills, job_title FROM companys WHERE login = ?",
                    login
            );

            if (companyData.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Компания не найдена");
            }

//            int companyId = (Integer) companyData.get(0).get("id");

            // Получаем вакансии компании
            List<Map<String, Object>> vacancies = jdbcTemplate.queryForList(
                    "SELECT id, activity, experience, skills, job_title FROM companys WHERE login = ?",
                    login
            );
            Long companyId = vacancies.id;
            List<Map<String, Object>> responses = jdbcTemplate.queryForList(
                    "SELECT applicants.first_name, applicants.number FROM responses JOIN applicants ON responses.applicant_id = applicants.id WHERE responses.company_id = ?", companyId
            );



//            // Получаем отклики на вакансии компании
//            List<Map<String, Object>> responses = jdbcTemplate.queryForList(
//                    "SELECT applicants.first_name, applicants.last_name, applicants.number " +
//                            "FROM responses " +
//                            "JOIN applicants ON responses.applicant_id = applicants.id " +
//                            "WHERE responses.company_id = ?",
//                    login
//            );

            // Формируем ответ
            Map<String, Object> result = Map.of(
                    "company", companyData.get(0),
                    "vacancies", vacancies,
                    "responses", responses
            );

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при получении данных: " + e.getMessage());
        }
    }
//
//    @PostMapping("/deletecomp")
//    @Transactional
//    public ResponseEntity<String> deleteVacancy(@RequestBody DeleteRequest deleteRequest) {
//
//    }



}