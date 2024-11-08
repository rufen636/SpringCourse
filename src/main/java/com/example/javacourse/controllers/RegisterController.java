package com.example.javacourse.controllers;

import com.example.javacourse.models.User;
import com.example.javacourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.javacourse.requests.UserRegistrationRequest;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        System.out.println("Received registration request for login: " + request.getLogin());
        if (userRepository.existsByLogin(request.getLogin())) {
            return new ResponseEntity<>("Логин уже используется", HttpStatus.CONFLICT);
        }



        List<String> roles = request.getRoles();
        for (String role : roles) {
            User newUser = new User();
            newUser.setLogin(request.getLogin());
            newUser.setPassword(request.getPassword()); // Учтите, что пароли лучше хэшировать
            newUser.setRole(role);
            userRepository.save(newUser);
        }

        return new ResponseEntity<>("Пользователь успешно зарегистрирован", HttpStatus.CREATED);
    }
}
