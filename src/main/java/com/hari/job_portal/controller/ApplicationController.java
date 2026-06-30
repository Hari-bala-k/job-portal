package com.hari.job_portal.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hari.job_portal.entity.Application;
import com.hari.job_portal.entity.ApplicationStatus;
import com.hari.job_portal.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
       
      private final ApplicationService applicationService;

      public ApplicationController(ApplicationService applicationService) {
            this.applicationService = applicationService;
        }
      @PostMapping("/apply")
        public Application applyJob(@RequestParam Long userId, @RequestParam Long jobId) {
            return applicationService.applyJob(userId, jobId);
        }
      
      @PutMapping("/update-status/{id}")
      public Application updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
          return applicationService.updateApplicationStatus(id, status);
      }   
}
