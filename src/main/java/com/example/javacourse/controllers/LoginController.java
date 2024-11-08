package com.example.javacourse.controllers;

import com.example.javacourse.models.User;
import com.example.javacourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.javacourse.requests.UserLoginRequest;
import com.example.javacourse.responses.UserLoginResponse;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // Разрешите доступ с фронтенда
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {

        String login = request.getLogin();
        String password = request.getPassword();
        System.out.println("Login request received: " + request);
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user == null) {
            return new ResponseEntity<>("Неверный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Пользователь: " + user.getLogin() + ", Роль: " + user.getRole());



        UserLoginResponse response = new UserLoginResponse(user.getRole());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
