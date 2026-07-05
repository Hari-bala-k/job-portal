package com.hari.job_portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.UserRequestDTO;
import com.hari.job_portal.dto.UserResponseDTO;
import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.DuplicateResourceException;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.mapper.UserMapper;
import com.hari.job_portal.repository.UserRepository;

@Service
public class UserService{
      
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new DuplicateResourceException(
                "Email already exists: " + userRequestDTO.getEmail()
            );
        }
        User user = userMapper.toEntity(userRequestDTO);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

        public UserResponseDTO getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
            return userMapper.toResponse(user);
        }

        public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponseDTO> response = new ArrayList<>();

        for (User user : users) {
            response.add(userMapper.toResponse(user));
        }

        return response;
    }

    public void deleteUser(Long id){
            User user = userRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("User not found with ID: " + id));

            userRepository.delete(user);
   }

    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with ID: " + id));

        if (!existingUser.getEmail().equals(updatedUser.getEmail())
                && userRepository.existsByEmail(updatedUser.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: " + updatedUser.getEmail());
        }

        userMapper.updateEntity(updatedUser, existingUser);

        User savedUser = userRepository.save(existingUser);

        return userMapper.toResponse(savedUser);
    }
}
