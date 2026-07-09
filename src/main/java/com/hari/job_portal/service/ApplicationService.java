package com.hari.job_portal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.ApplicationResponseDTO;
import com.hari.job_portal.dto.ApplyRequestDTO;
import com.hari.job_portal.entity.Application;
import com.hari.job_portal.entity.ApplicationStatus;
import com.hari.job_portal.entity.Job;
import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.mapper.ApplicationMapper;
import com.hari.job_portal.repository.ApplicationRepository;
import com.hari.job_portal.repository.JobRepository;
import com.hari.job_portal.repository.UserRepository;

@Service
public class ApplicationService {
     
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationMapper = applicationMapper;
    }

    public ApplicationResponseDTO getApplicationById(Long id) {
        return applicationMapper.toResponse(
            applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id))
        );
    }
     
   public List<ApplicationResponseDTO> getAllApplications() {
      List<Application> applications = applicationRepository.findAll();
       List<ApplicationResponseDTO> applicationResponseDTO = new ArrayList<>();
      for (Application application : applications) {
        applicationResponseDTO.add(applicationMapper.toResponse(application));
    }
    return applicationResponseDTO;
}


    public ApplicationResponseDTO applyJob(ApplyRequestDTO request ) {
       
       User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + request.getUserId()));

       Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + request.getJobId())); 
                
        Application application = applicationMapper.toEntity();
        application.setUser(user);
        application.setJob(job);   
        application.setStatus(ApplicationStatus.APPLIED);
        application.setApplicationDate(LocalDate.now());
        Application savedApplication = applicationRepository.save(application);
         return applicationMapper.toResponse(savedApplication);
    }

    public ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatus status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
        application.setStatus(status);
        Application updatedApplication = applicationRepository.save(application);
        return applicationMapper.toResponse(updatedApplication);
    }
}