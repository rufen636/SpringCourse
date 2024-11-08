package com.example.javacourse.responses;

public class UserLoginResponse {
    private String role;

    public UserLoginResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
