package com.hari.job_portal.mapper;

import org.springframework.stereotype.Component;

import com.hari.job_portal.dto.UserRequestDTO;
import com.hari.job_portal.dto.UserResponseDTO;
import com.hari.job_portal.entity.User;

@Component
public class UserMapper {
      
    public User toEntity(UserRequestDTO userReqDTO) {
        User user = new User();
        user.setName(userReqDTO.getName());
        user.setPhone(userReqDTO.getPhone());
        user.setEmail(userReqDTO.getEmail());
        user.setPassword(userReqDTO.getPassword());
        user.setRole(userReqDTO.getRole());
        return user;
    }

    public UserResponseDTO toResponse(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }

    public void updateEntity(UserRequestDTO userReqDTO, User existingUser) {
        existingUser.setName(userReqDTO.getName());
        existingUser.setPhone(userReqDTO.getPhone());
        existingUser.setEmail(userReqDTO.getEmail());
        existingUser.setPassword(userReqDTO.getPassword());
        existingUser.setRole(userReqDTO.getRole());
    }
}
