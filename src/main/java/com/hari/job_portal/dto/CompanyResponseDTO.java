package com.hari.job_portal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String location;
}
