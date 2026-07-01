package com.hari.job_portal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.job_portal.dto.UserRequestDTO;
import com.hari.job_portal.entity.User;
import com.hari.job_portal.exception.DuplicateResourceException;
import com.hari.job_portal.exception.ResourceNotFoundException;
import com.hari.job_portal.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    // CREATE USER
    public User saveUser(UserRequestDTO dto) {


        if(userRepository.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: " + dto.getEmail()
            );
        }


        User user = mapToEntity(dto);

        return userRepository.save(user);
    }




    // GET USER BY ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with ID: " + id
                    )
                );
    }




    // GET ALL USERS
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }





    // DELETE USER
    public void deleteUser(Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with ID: " + id
                    )
                );


        userRepository.delete(user);
    }





    // UPDATE USER
    public User updateUser(Long id, UserRequestDTO dto) {


        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with ID: " + id
                    )
                );



        if(!existingUser.getEmail().equals(dto.getEmail())
                && userRepository.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: " + dto.getEmail()
            );
        }



        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(dto.getPassword());
        existingUser.setPhone(dto.getPhone());
        existingUser.setRole(dto.getRole());


        return userRepository.save(existingUser);
    }





    private User mapToEntity(UserRequestDTO dto) {


        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());


        return user;
    }

}