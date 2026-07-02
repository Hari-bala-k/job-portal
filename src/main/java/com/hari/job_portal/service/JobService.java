package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.JobRequestDTO;
import com.hari.job_portal.entity.Company;
import com.hari.job_portal.entity.Job;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.CompanyRepository;
import com.hari.job_portal.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job saveJob(JobRequestDTO jobRequestDTO) {
        Company company = companyRepository.findById(jobRequestDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + jobRequestDTO.getCompanyId()));


        Job job = new Job();
        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setSalary(jobRequestDTO.getSalary());
        job.setExperience(jobRequestDTO.getExperience());
        job.setLocation(jobRequestDTO.getLocation());
        job.setCompany(company);
        return jobRepository.save(job);
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Long id) {
        jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
        jobRepository.deleteById(id);
    }

    public Job updateJob(Long id, JobRequestDTO updatedJob) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));

        Company company = companyRepository.findById(updatedJob.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + updatedJob.getCompanyId()));
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setExperience(updatedJob.getExperience());
        existingJob.setCompany(company);

        return jobRepository.save(existingJob);
    }
}
