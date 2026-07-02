package com.hari.job_portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Salary is required")
    private Double salary;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}