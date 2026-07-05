package com.hari.job_portal.mapper;

import org.springframework.stereotype.Component;

import com.hari.job_portal.dto.CompanyRequestDTO;
import com.hari.job_portal.dto.CompanyResponseDTO;
import com.hari.job_portal.entity.Company;

@Component
public class CompanyMapper {
   public Company toEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.getName());
        company.setDescription(companyRequestDTO.getDescription());
        company.setLocation(companyRequestDTO.getLocation());
        company.setWebsite(companyRequestDTO.getWebsite());
        return company;
    }

    public CompanyResponseDTO toResponse(Company company) {
        CompanyResponseDTO response = new CompanyResponseDTO();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setDescription(company.getDescription());
        response.setLocation(company.getLocation());
        response.setWebsite(company.getWebsite());
        return response;
    }

    public void updateEntity(Company company, CompanyRequestDTO companyRequestDTO) {
        company.setName(companyRequestDTO.getName());
        company.setDescription(companyRequestDTO.getDescription());
        company.setLocation(companyRequestDTO.getLocation());
        company.setWebsite(companyRequestDTO.getWebsite());
    }
}
