package com.example.javacourse.models;

import java.util.List;

public class CompanyResponse {
    private Company company;
    private List<Vacancy> vacancies;
    private List<ResponseData> responses;

    // Геттеры и сеттеры
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public List<ResponseData> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseData> responses) {
        this.responses = responses;
    }
}
