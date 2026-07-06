package com.hari.job_portal.mapper;

import org.springframework.stereotype.Component;

import com.hari.job_portal.dto.JobRequestDTO;
import com.hari.job_portal.dto.JobResponseDTO;
import com.hari.job_portal.entity.Job;

@Component
public class JobMapper {
      
    public Job toEntity(JobRequestDTO jobRequestDTO) {
        Job job = new Job();
        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setSalary(jobRequestDTO.getSalary());
        job.setExperience(jobRequestDTO.getExperience());
        job.setLocation(jobRequestDTO.getLocation());
        return job;
    }
    
    public JobResponseDTO toResponse(Job job) {
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(job.getId());
        jobResponseDTO.setTitle(job.getTitle());
        jobResponseDTO.setDescription(job.getDescription());
        jobResponseDTO.setSalary(job.getSalary());
        jobResponseDTO.setExperience(job.getExperience());
        jobResponseDTO.setLocation(job.getLocation());
        
        if (job.getCompany() != null) {
            jobResponseDTO.setCompanyId(job.getCompany().getId());
            jobResponseDTO.setCompanyName(job.getCompany().getName());
        }
        
        return jobResponseDTO;
    }
    
    public void updateEntity(Job existingJob, JobRequestDTO updatedJob) {
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setExperience(updatedJob.getExperience());
        existingJob.setLocation(updatedJob.getLocation());
    }
}
