package com.hari.job_portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.job_portal.dto.JobRequestDTO;
import com.hari.job_portal.dto.JobResponseDTO;
import com.hari.job_portal.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
      
     private final JobService jobService;

     public JobController(JobService jobService) {
         this.jobService = jobService;
     }

     @PostMapping
     public JobResponseDTO createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
         return jobService.saveJob(jobRequestDTO);
     }
     
     @GetMapping
        public List<JobResponseDTO> getAllJobs() {
            return jobService.getAllJobs();
        }
     @GetMapping("/{id}")
     public JobResponseDTO getJobById(@PathVariable Long id) {
         return jobService.getJobById(id);
     }

     @PutMapping("/{id}")
     public JobResponseDTO updateJob(@PathVariable Long id, @Valid @RequestBody JobRequestDTO jobRequestDTO) {
         return jobService.updateJob(id, jobRequestDTO);
     }

     @DeleteMapping("/{id}")
     public void deleteJob(@PathVariable Long id) {
         jobService.deleteJob(id);
     }  
     
}
