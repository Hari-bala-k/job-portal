package com.hari.job_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.CompanyRequestDTO;
import com.hari.job_portal.dto.CompanyResponseDTO;
import com.hari.job_portal.entity.Company;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.mapper.CompanyMapper;
import com.hari.job_portal.repository.CompanyRepository;

@Service
public class CompanyService {

     private final CompanyRepository companyRepository;
     private final CompanyMapper companyMapper;

     public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyResponseDTO saveCompany(CompanyRequestDTO companyRequestDTO) {
        Company company = companyMapper.toEntity(companyRequestDTO);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponse(savedCompany);
    }

    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
        return companyMapper.toResponse(company);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
        companyRepository.delete(company);
    }

    public List<CompanyResponseDTO> getAllCompanies() {
       List<Company> companies = companyRepository.findAll();
        
       List<CompanyResponseDTO> response = new ArrayList<>();
        
       for (Company company : companies) {
            response.add(companyMapper.toResponse(company));
        }
        return response;
    }

    public CompanyResponseDTO updateCompany(Long id, CompanyRequestDTO updatedCompany) {
        
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));

        companyMapper.updateEntity(existingCompany, updatedCompany);
        Company updated = companyRepository.save(existingCompany);
        return companyMapper.toResponse(updated);
    }
}
