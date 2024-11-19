package com.example.javacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class GetVacanciesController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/allCompanysData")
    public ResponseEntity<List<Map<String, Object>>> getAllCompanys() {
        String baseQuery = "SELECT id, name_company, activity, experience, skills, job_title FROM companys";
        List<Map<String, Object>> companyList = jdbcTemplate.queryForList(baseQuery);
        return ResponseEntity.ok(companyList);
    }
    @PostMapping("/respond")
    public ResponseEntity<?> handleRespond(@RequestBody Map<String, Object> requestBody) {
        try {
            // Извлечение данных из тела запроса
            Long applicantId = ((Number) requestBody.get("applicant_id")).longValue();
            Long companyId = ((Number) requestBody.get("company_id")).longValue();

            // Проверка на существующий отклик
            String checkQuery = "SELECT * FROM responses WHERE applicant_id = ? AND company_id = ?";
            List<Map<String, Object>> existingResponse = jdbcTemplate.queryForList(checkQuery, applicantId, companyId);

            if (!existingResponse.isEmpty()) {
                return ResponseEntity.status(400).body(Map.of("error", "Вы уже откликались на эту компанию"));
            }

            // Вставка нового отклика
            String insertQuery = "INSERT INTO responses (applicant_id, company_id) VALUES (?, ?)";
            jdbcTemplate.update(insertQuery, applicantId, companyId);

            return ResponseEntity.status(201).body(Map.of("message", "Отклик успешно отправлен"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Ошибка при отправке отклика"));
        }
    }
}
