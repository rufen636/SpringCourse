package com.example.javacourse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;
    private String password;
    private String role;



    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return this.login; // или как у вас названо поле логина
    }


    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
// геттеры и сеттеры
}
