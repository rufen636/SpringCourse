package com.example.javacourse.models;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String first_name; // Используйте camelCase для полей
    private String second_name;
    private String surname;
    private int experience;
    private String skills;
    private String field_of_work;
    private String login;// Исправлено на camelCase
    private String number;

    public String getLogin() {
        return login;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getSurname() {
        return surname;
    }

    public int getExperience() {
        return experience;
    }

    public String getField_of_work() {
        return field_of_work;
    }
// Getters and Setters

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setField_of_work(String field_of_work) {
        this.field_of_work = field_of_work;
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


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
