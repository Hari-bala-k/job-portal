package com.hari.job_portal.dto;

import com.hari.job_portal.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Role role;
}
