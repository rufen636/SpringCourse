package com.example.javacourse.repository;

import com.example.javacourse.models.ResponseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseData, Long> {

//    @Query("SELECT new com.example.javacourse.models.ResponseData(a.firstName, a.number) " +
//            "FROM ResponseData r " +
//            "JOIN r.applicant a " +
//            "WHERE r.company.id = :companyId")
//    List<ResponseData> findResponsesByCompanyId(Long companyId);
}
