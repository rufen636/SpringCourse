package com.example.javacourse.requests;

public class DeleteRequest {
    private String login;
    private Long id;

    // Геттеры и сеттеры

    public String getLogin() {
        return login;
    }

    public Long getVacancyId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
