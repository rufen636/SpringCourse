package com.example.javacourse.requests;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigInteger;

public class DeleteVacancyRequest {
    private BigInteger id;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
