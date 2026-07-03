package com.hari.job_portal.dto;

import com.hari.job_portal.entity.ApplicationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateApplicationRequestDTO {
    @NotNull(message = "Status is required")
    private ApplicationStatus status;
}
