package com.example.javacourse.services;

import com.example.javacourse.models.Company;
import com.example.javacourse.models.Vacancy;
import com.example.javacourse.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Vacancy> getVacanciesByCompanyLogin(String login) {
        Company company = companyRepository.findByLogin(login).orElse(null);
        if (company != null) {
            Vacancy vacancy = new Vacancy(
                    company.getActivity(),
                    company.getExperience(),
                    company.getSkills(),
                    company.getJob_title());
            return List.of(vacancy);
        }
        return List.of();
    }
}
