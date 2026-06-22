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

import com.hari.job_portal.entity.Job;
import com.hari.job_portal.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
      
     private final JobService jobService;

     public JobController(JobService jobService) {
         this.jobService = jobService;
     }

     @PostMapping
     public Job createJob(@RequestBody Job job) {
         return jobService.saveJob(job);
     }
     
     @GetMapping
        public List<Job> getAllJobs() {
            return jobService.getAllJobs();
        }
     @GetMapping("/{id}")
     public Job getJobById(@PathVariable Long id) {
         return jobService.getJobById(id);
     }

     @PutMapping("/{id}")
     public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
         return jobService.updateJob(id, updatedJob);
     }

     @DeleteMapping("/{id}")
     public void deleteJob(@PathVariable Long id) {
         jobService.deleteJob(id);
     }  
     
}
