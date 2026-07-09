package com.hari.job_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.JobRequestDTO;
import com.hari.job_portal.dto.JobResponseDTO;
import com.hari.job_portal.entity.Company;
import com.hari.job_portal.entity.Job;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.mapper.JobMapper;
import com.hari.job_portal.repository.CompanyRepository;
import com.hari.job_portal.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobMapper = jobMapper;
    }

    public JobResponseDTO saveJob(JobRequestDTO jobRequestDTO) {
        Company company = companyRepository.findById(jobRequestDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + jobRequestDTO.getCompanyId()));

        Job job = jobMapper.toEntity(jobRequestDTO);
        job.setCompany(company);
        Job savedJob = jobRepository.save(job);
        return jobMapper.toResponse(savedJob);
    }

    public JobResponseDTO getJobById(Long id){
        return jobMapper.toResponse(jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id)));
    }

    public List<JobResponseDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobResponseDTO> jobResponses = new ArrayList<>();
         for(Job job : jobs){
            jobResponses.add(jobMapper.toResponse(job));
         }
         return jobResponses;
    }

    public void deleteJob(Long id) {
        jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
        jobRepository.deleteById(id);
    }

   public JobResponseDTO updateJob(Long id, JobRequestDTO updatedJobDTO) {

    Job existingJob = jobRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Job not found with ID: " + id));

    Company company = companyRepository.findById(updatedJobDTO.getCompanyId())
            .orElseThrow(() ->
                    new ResourceNotFoundException("Company not found with ID: "
                            + updatedJobDTO.getCompanyId()));

     jobMapper.updateEntity(existingJob, updatedJobDTO);
     existingJob.setCompany(company);

    Job savedJob = jobRepository.save(existingJob);

    return jobMapper.toResponse(savedJob);
}
}
