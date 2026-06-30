package com.hari.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.job_portal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
