package com.example.javacourse.requests;

import java.math.BigInteger;

public class UserLoginRequest {
    private String login;
    private String password;
    private BigInteger userId;

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

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
