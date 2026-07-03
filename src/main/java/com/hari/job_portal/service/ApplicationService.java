package com.hari.job_portal.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.ApplyRequestDTO;
import com.hari.job_portal.dto.UpdateApplicationRequestDTO;
import com.hari.job_portal.entity.Application;
import com.hari.job_portal.entity.ApplicationStatus;
import com.hari.job_portal.entity.Job;
import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.ApplicationRepository;
import com.hari.job_portal.repository.JobRepository;
import com.hari.job_portal.repository.UserRepository;

@Service
public class ApplicationService {
     
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public Application applyJob(ApplyRequestDTO request ) {
        Application application = new Application();
       
       User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + request.getUserId()));

       Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + request.getJobId())); 
                
        application.setJob(job);
        application.setUser(user);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setApplicationDate(LocalDate.now());
        return applicationRepository.save(application);
    }

    public Application updateApplicationStatus(Long applicationId, UpdateApplicationRequestDTO updateRequest) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + applicationId));
        application.setStatus(updateRequest.getStatus());
        return applicationRepository.save(application);
    }
}