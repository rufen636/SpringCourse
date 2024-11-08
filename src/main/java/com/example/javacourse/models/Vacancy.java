package com.example.javacourse.models;

import jakarta.persistence.*;

@Entity
@Table(name = "companys")
public class Vacancy {

    private String activity;
    private int experience;
    private String skills;
    private String job_title;
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Конструктор
    public Vacancy(String activity, int experience, String skills, String job_title) {
        this.activity = activity;
        this.experience = experience;
        this.skills = skills;
        this.job_title = job_title;
    }

    public Vacancy() {

    }

    // Геттеры и сеттеры
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getJobTitle() {
        return job_title;
    }

    public void setJobTitle(String jobTitle) {
        this.job_title = jobTitle;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
