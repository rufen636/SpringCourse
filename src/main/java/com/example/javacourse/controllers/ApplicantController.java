package com.example.javacourse.controllers;

import com.example.javacourse.models.Applicant;
import com.example.javacourse.models.User;
import com.example.javacourse.repository.ApplicantRepository;
import com.example.javacourse.repository.UserRepository;
import com.example.javacourse.requests.UserLoginRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ApplicantController {

    @Autowired
    private UserRepository userRepository;  // Внедряем userRepository

    @Autowired
    private ApplicantRepository applicantRepository;

    @PostMapping("/applicant")
    public ResponseEntity<String> saveApplicant(@RequestBody Applicant applicant) {
        try {
            // Ищем пользователя по логину
            Optional<User> userOptional = userRepository.findByLogin(applicant.getLogin());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User user = userOptional.get();
            System.out.println("User role: " + user.getRole()); // Логируем роль пользователя

            if (!"applicant".equals(user.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
            }

            // Устанавливаем пользователя для анкеты


            // Устанавливаем другие данные для анкеты
            applicant.setFirst_name(applicant.getFirst_name());
            applicant.setSecond_name(applicant.getSecond_name());
            applicant.setSurname(applicant.getSurname());
            applicant.setExperience(applicant.getExperience());
            applicant.setSkills(applicant.getSkills());
            applicant.setField_of_work(applicant.getField_of_work());
            applicant.setNumber(applicant.getNumber());

            // Сохраняем анкету
            applicantRepository.save(applicant);

            return ResponseEntity.status(HttpStatus.CREATED).body("Applicant saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving applicant");
        }
    }
    @PostMapping("/applicantTake")
    @Transactional
    public ResponseEntity<Applicant> getApplicant(@RequestBody UserLoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        BigInteger id = loginRequest.getUserId();
        // Пытаемся найти пользователя по логину
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Проверка роли пользователя
            if ("applicant".equals(user.getRole())) {
                // Находим анкету соискателя
                Optional<Applicant> applicantOptional = applicantRepository.findByLogin(login);
                if (applicantOptional.isPresent()) {
                    return ResponseEntity.ok(applicantOptional.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Анкета не найдена
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Доступ запрещен
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Пользователь не найден
        }
    }


}