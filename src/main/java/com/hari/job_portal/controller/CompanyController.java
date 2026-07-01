package com.hari.job_portal.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.job_portal.dto.CompanyRequestDTO;
import com.hari.job_portal.entity.Company;
import com.hari.job_portal.service.CompanyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/companies")
public class CompanyController {
      
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;   
    }
    
    @PostMapping
    public Company createCompany( @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.saveCompany(companyRequestDTO);
    }
    
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.updateCompany(id, companyRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
