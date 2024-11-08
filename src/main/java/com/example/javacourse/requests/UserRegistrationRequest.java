package com.example.javacourse.requests;

import java.util.List;

public class UserRegistrationRequest {
    private String login;
    private String password;
    private String confirmPassword;
    private List<String> roles;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public List<String> getRoles() {
        return roles;
    }

}
