package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.CompanyRequestDTO;
import com.hari.job_portal.entity.Company;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.CompanyRepository;

@Service
public class CompanyService {

     private final CompanyRepository companyRepository;

     public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.getName());
        company.setDescription(companyRequestDTO.getDescription());
        company.setLocation(companyRequestDTO.getLocation());
        company.setWebsite(companyRequestDTO.getWebsite());
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
    }

    public void deleteCompany(Long id) {
        companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
        companyRepository.deleteById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(Long id, CompanyRequestDTO updatedCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setDescription(updatedCompany.getDescription());
        existingCompany.setLocation(updatedCompany.getLocation());
        existingCompany.setWebsite(updatedCompany.getWebsite());

        return companyRepository.save(existingCompany);
    }
}
