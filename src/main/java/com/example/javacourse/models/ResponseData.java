package com.example.javacourse.models;

import jakarta.persistence.*;



@Entity
@Table(name = "responses")
public class ResponseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant; // Связь с сущностью Applicant

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // Связь с сущностью Company

    // Геттеры и сеттеры

    public void setId(Long id) {
        this.id = id;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Company getCompany() {
        return company;
    }
}
