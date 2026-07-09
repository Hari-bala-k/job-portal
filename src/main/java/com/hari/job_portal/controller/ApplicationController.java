package com.hari.job_portal.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.job_portal.dto.ApplicationResponseDTO;
import com.hari.job_portal.dto.ApplyRequestDTO;
import com.hari.job_portal.dto.UpdateApplicationRequestDTO;
import com.hari.job_portal.service.ApplicationService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
       
      private final ApplicationService applicationService;

      public ApplicationController(ApplicationService applicationService) {
            this.applicationService = applicationService;
        }
        @GetMapping("/{id}")
        public ApplicationResponseDTO getApplicationById(@PathVariable Long id) {
            return applicationService.getApplicationById(id);
        }
        @GetMapping
        public List<ApplicationResponseDTO> getApplications() {
            return applicationService.getAllApplications();
        }
      @PostMapping("/apply")
        public ApplicationResponseDTO applyJob(@Valid @RequestBody ApplyRequestDTO request) {
            return applicationService.applyJob(request);
        }
        
        @PutMapping("/{id}")
        public ApplicationResponseDTO updateApplicationStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateApplicationRequestDTO request) {

        return applicationService.updateApplicationStatus(id, request.getStatus());
    }

}
