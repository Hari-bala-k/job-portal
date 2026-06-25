package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.UserRequestDTO;
import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.DuplicateResourceException;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.UserRepository;

@Service
public class UserService{
      
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequestDTO userRequestDTO) {

         if(userRepository.existsByEmail(userRequestDTO.getEmail())) {
           throw new DuplicateResourceException(
            "Email already exists: " + userRequestDTO.getEmail()
           );
        }
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        user.setRole(userRequestDTO.getRole());
           
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id){

    userRepository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException("User not found with ID: " + id)
        );

    userRepository.deleteById(id);
   }

   public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        
    existingUser.setName(updatedUser.getName());
    existingUser.setEmail(updatedUser.getEmail());
    existingUser.setPassword(updatedUser.getPassword());
    existingUser.setRole(updatedUser.getRole());
    existingUser.setPhone(updatedUser.getPhone());

        return userRepository.save(existingUser);
    }
}
