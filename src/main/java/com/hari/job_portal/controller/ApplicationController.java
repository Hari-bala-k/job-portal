package com.hari.job_portal.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.job_portal.dto.ApplyRequestDTO;
import com.hari.job_portal.dto.UpdateApplicationRequestDTO;
import com.hari.job_portal.entity.Application;
import com.hari.job_portal.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
       
      private final ApplicationService applicationService;

      public ApplicationController(ApplicationService applicationService) {
            this.applicationService = applicationService;
        }
      @PostMapping("/apply")
        public Application applyJob(@Valid @RequestBody ApplyRequestDTO request) {
            return applicationService.applyJob(request);
        }
      
      @PutMapping("/update-status/{id}")
      public Application updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateApplicationRequestDTO updateRequest) {
          return applicationService.updateApplicationStatus(id, updateRequest);
      }   
}
