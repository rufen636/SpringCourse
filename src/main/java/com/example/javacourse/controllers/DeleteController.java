package com.example.javacourse.controllers;

import com.example.javacourse.models.Company;
import com.example.javacourse.repository.CompanyRepository;
import com.example.javacourse.requests.DeleteVacancyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class DeleteController {


    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/deletecomp")
    public ResponseEntity<?> deleteVacancy(@RequestBody DeleteVacancyRequest vacancyRequest) {


        if (vacancyRequest == null || vacancyRequest.getId() == null) {
            System.out.println("Ошибка: vacancyRequest или vacancyId равен null");
            return ResponseEntity.badRequest().body("Invalid request: vacancyId is missing.");
        }

        BigInteger id = vacancyRequest.getId();
        id.toString();
        System.out.println("ID вакансии для удаления: " + id);

        try {
            // Удаляем вакансию по её ID
            int rowsAffected = jdbcTemplate.update(
                    "DELETE FROM companys WHERE id = ?",
                    id
            );

            if (rowsAffected > 0) {
                return ResponseEntity.ok("Вакансия успешно удалена");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Вакансия с указанным ID не найдена");
            }
        } catch (Exception e) {
            // Обработка ошибок и возврат 500 с текстом ошибки
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при удалении вакансии: " + e.getMessage());
        }
    }
    @PostMapping("/deleteappl")
    public ResponseEntity<?> deleteFormApplicant(@RequestBody DeleteVacancyRequest vacancyRequest) {


        if (vacancyRequest == null || vacancyRequest.getId() == null) {
            System.out.println("Ошибка: vacancyRequest или vacancyId равен null");
            return ResponseEntity.badRequest().body("Invalid request: vacancyId is missing.");
        }

        BigInteger id = vacancyRequest.getId();
        id.toString();
        System.out.println("ID вакансии для удаления: " + id);

        try {
            // Удаляем вакансию по её ID
            int rowsAffected = jdbcTemplate.update(
                    "DELETE  FROM applicants WHERE id = ?",
                    id
            );

            if (rowsAffected > 0) {
                return ResponseEntity.ok("Вакансия успешно удалена");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Вакансия с указанным ID не найдена");
            }
        } catch (Exception e) {
            // Обработка ошибок и возврат 500 с текстом ошибки
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при удалении вакансии: " + e.getMessage());
        }
    }
}
