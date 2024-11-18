package com.example.javacourse.controllers;


import com.example.javacourse.models.Company;
import com.example.javacourse.repository.CompanyRepository;
import com.example.javacourse.requests.DeleteVacancyRequest;
import com.example.javacourse.requests.FindRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class FindController {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/findCandidates")
    public ResponseEntity<?> findCandidates(@RequestBody FindRequest findRequest) {
        String baseQuery = "SELECT * FROM applicants WHERE (first_name LIKE ? OR field_of_work LIKE ? OR skills LIKE ?)";
        List<Object> params = new ArrayList<>();
        params.add("%" + findRequest.getQuery() + "%");
        params.add("%" + findRequest.getQuery() + "%");
        params.add("%" + findRequest.getQuery() + "%");

        if (findRequest.getQuery() != null && findRequest.getQuery().isEmpty() && findRequest.getExperienceFilters() != null && !findRequest.getExperienceFilters().isEmpty()) {
            String experienceCondition = findRequest.getExperienceFilters()
                    .stream()
                    .map(filter -> "experience = ?")
                    .collect(Collectors.joining(" OR "));
            baseQuery += " AND (" + experienceCondition + ")";
            params.addAll(findRequest.getExperienceFilters());
        }

        try {
            List<Map<String, Object>> candidates = jdbcTemplate.queryForList(baseQuery, params.toArray());
            return ResponseEntity.ok(candidates);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при поиске кандидатов");
        }
    }
}