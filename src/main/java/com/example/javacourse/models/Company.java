package com.example.javacourse.models;

import jakarta.persistence.*;


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

    private String vacancies; // Предположим, что вакансии хранятся в виде строки, разделённой запятыми

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }

    public String getName_company() {
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
