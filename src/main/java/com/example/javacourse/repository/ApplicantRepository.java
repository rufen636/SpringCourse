package com.example.javacourse.repository;

import com.example.javacourse.models.Applicant;
import com.example.javacourse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByLogin(String login);
}
