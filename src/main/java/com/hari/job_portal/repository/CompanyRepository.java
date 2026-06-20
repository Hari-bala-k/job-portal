package com.hari.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.job_portal.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
