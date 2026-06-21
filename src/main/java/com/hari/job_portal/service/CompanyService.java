package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.entity.Company;
import com.hari.job_portal.repository.CompanyRepository;

@Service
public class CompanyService {

     private final CompanyRepository companyRepository;

     public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
    }

    public void deleteCompany(Long id) {
        companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + id));
        companyRepository.deleteById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
