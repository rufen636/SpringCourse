package com.example.javacourse.services;

import com.example.javacourse.models.Company;
import com.example.javacourse.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyDataByLogin(String login) {
        // Ищем компанию по логину и возвращаем всю информацию о ней
        return companyRepository.findByLogin(login).orElse(null);
    }
}

