package com.example.javacourse.requests;

import jakarta.persistence.criteria.CriteriaBuilder;

public class DeleteVacancyRequest {
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
