package com.example.javacourse.controllers;

import com.example.javacourse.models.*;
import com.example.javacourse.repository.ApplicantRepository;
import com.example.javacourse.repository.CompanyRepository;
import com.example.javacourse.repository.UserRepository;
import com.example.javacourse.repository.VacancyRepository;
import com.example.javacourse.requests.DeleteRequest;
import com.example.javacourse.requests.LoginRequest;
import com.example.javacourse.requests.UserLoginRequest;
import com.example.javacourse.services.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private VacancyRepository vacancyRepository;

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

            // Устанавливаем пользователя для анкеты
            company.setUser(user);

            company.setName_company(company.getName_company());
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
    public ResponseEntity<?> getCompanyData(@RequestParam UserLoginRequest loginRequest) {
        String login = loginRequest.getLogin();

        // Проверка логина
        if (login == null || login.isEmpty()) {
            return ResponseEntity.badRequest().body("Логин не указан");
        }

        // Логика работы с пользователем
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if ("company".equals(user.getRole())) {
                Optional<Company> companyOptional = companyRepository.findByLogin(login);
                if (companyOptional.isPresent()) {
                    Company company = companyOptional.get();
                    Map<String, Object> response = new HashMap<>();
                    response.put("company", company);
                    response.put("vacancies", company.getVacancies()); // Предполагается, что есть метод getVacancies
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Анкета не найдена");
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
    }


    @PostMapping("/deletecomp")
    @Transactional
    public ResponseEntity<String> deleteVacancy(@RequestBody DeleteRequest deleteRequest) {
        String login = deleteRequest.getLogin();
        Long vacancyId = deleteRequest.getVacancyId();

        // Проверка на null
        if (login == null || vacancyId == null) {
            return ResponseEntity.badRequest().body("Login или ID вакансии не могут быть null");
        }

        // Проверяем, существует ли пользователь с таким логином
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден.");
        }

        User user = userOptional.get();

        // Проверяем, что пользователь является компанией
        if (!"company".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен.");
        }

        // Получаем компанию по логину
        Optional<Company> companyOptional = companyRepository.findByLogin(login);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Компания не найдена.");
        }

        Long companyId = companyOptional.get().getId();

        // Проверяем, существует ли вакансия с указанным ID
        Optional<Vacancy> vacancyOptional = vacancyRepository.findByIdAndCompanyId(vacancyId, companyId);
        if (!vacancyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Вакансия с ID " + vacancyId + " не найдена для компании с ID " + companyId + ".");
        }

        // Удаляем вакансию
        vacancyRepository.delete(vacancyOptional.get());
        return ResponseEntity.ok("Вакансия успешно удалена.");
    }




}