package com.example.javacourse.repository;



import com.example.javacourse.models.Applicant;
import com.example.javacourse.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByLogin(String login);


}