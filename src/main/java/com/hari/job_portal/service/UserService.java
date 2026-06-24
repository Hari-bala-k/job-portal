package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.UserRepository;

@Service
public class UserService{
      
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user)  {
        if(userRepository.existsByEmail(user.getEmail())) {
           throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
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
