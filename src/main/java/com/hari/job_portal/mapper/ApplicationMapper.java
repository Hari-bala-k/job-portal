package com.hari.job_portal.mapper;

import org.springframework.stereotype.Component;

import com.hari.job_portal.dto.ApplicationResponseDTO;
import com.hari.job_portal.entity.Application;

@Component
public class ApplicationMapper {
    
    public Application toEntity() {
       return new Application();
    }

   public ApplicationResponseDTO toResponse(Application application) {
       
    ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
    applicationResponseDTO.setId(application.getId());
    applicationResponseDTO.setStatus(application.getStatus());
    applicationResponseDTO.setApplicationDate(application.getApplicationDate());

    if (application.getUser() != null) {
        applicationResponseDTO.setUserId(application.getUser().getId());
        applicationResponseDTO.setUserName(application.getUser().getName());
    }
    if (application.getJob() != null) {
        applicationResponseDTO.setJobId(application.getJob().getId());
        applicationResponseDTO.setJobTitle(application.getJob().getTitle());
    }

    return applicationResponseDTO;
}

}
