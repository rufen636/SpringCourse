package com.example.javacourse.requests;

public class UserLoginRequest {
    private String login;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Геттер и сеттер
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
