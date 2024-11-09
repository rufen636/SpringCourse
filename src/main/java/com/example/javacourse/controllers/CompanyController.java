package com.example.javacourse.controllers;

import com.example.javacourse.models.*;
import com.example.javacourse.repository.CompanyRepository;
import com.example.javacourse.repository.UserRepository;
import com.example.javacourse.requests.DeleteRequest;
import com.example.javacourse.requests.UserLoginRequest;
import com.example.javacourse.services.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<?> getCompanyData(@RequestBody UserLoginRequest loginRequest) {
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

                    // Создаем ответ с данными компании
                    Map<String, Object> response = new HashMap<>();
                    response.put("companyName", company.getName_company());
                    response.put("activity", company.getActivity());
                    response.put("experience", company.getExperience());
                    response.put("skills", company.getSkills());
                    response.put("jobTitle", company.getJob_title());

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


//
//    @PostMapping("/deletecomp")
//    @Transactional
//    public ResponseEntity<String> deleteVacancy(@RequestBody DeleteRequest deleteRequest) {
//
//    }



}