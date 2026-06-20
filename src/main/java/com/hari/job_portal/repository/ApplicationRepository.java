package com.hari.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.job_portal.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
