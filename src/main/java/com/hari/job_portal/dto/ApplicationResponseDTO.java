package com.hari.job_portal.dto;

import java.time.LocalDate;

import com.hari.job_portal.entity.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationResponseDTO {
     private Long id;
     private ApplicationStatus  status;
     private  LocalDate applicationDate;
     
     private Long userId;
     private String userName;

     private Long jobId;
     private String jobTitle;

}
