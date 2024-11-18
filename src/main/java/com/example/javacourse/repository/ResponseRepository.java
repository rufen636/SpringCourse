package com.example.javacourse.repository;

import com.example.javacourse.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByVacancyId(Long vacancyId);
}

