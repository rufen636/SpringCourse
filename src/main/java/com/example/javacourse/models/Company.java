package com.example.javacourse.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name_company;
    private String activity;
    private int experience;
    private String skills;
    private String login;
    private String job_title;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Vacancy> vacancies = new ArrayList<>();

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
    public String getName() {
        return name_company;
    }

    public String getActivity() {
        return activity;
    }

    public int getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }

    public String getLogin() {
        return login;
    }

    public String getJob_title() {
        return job_title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }


}
