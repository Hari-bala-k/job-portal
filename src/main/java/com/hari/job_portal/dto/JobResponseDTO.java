package com.hari.job_portal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String experience;
    private String location;

    private Long companyId;
    private String companyName;

}
