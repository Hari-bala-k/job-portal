package com.hari.job_portal.dto;



import com.hari.job_portal.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
  @Getter
  @Setter
  public class UserRequestDTO {

     @NotBlank(message = "Name is required")
     private String name;
     @NotBlank(message = "Email is required")
     @Email(message = "Email should be valid")
     private String email;
     @NotBlank(message = "Password is required")
     private String password;
     @NotBlank(message = "Phone is required")
     @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 characters")
     private String phone;
     @NotNull(message = "Role is required")
     private Role role;
     
}
