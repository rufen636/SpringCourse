package com.example.javacourse.repository;

import com.example.javacourse.models.ResponseData;
import com.example.javacourse.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByCompanyId(Long company_id); // Метод для получения вакансий по ID компании
    @Query("SELECT v FROM Vacancy v WHERE v.id = :id AND v.company.id = :companyId")
    Optional<Vacancy> findByIdAndCompanyId(@Param("id") Long id, @Param("companyId") Long companyId);


}