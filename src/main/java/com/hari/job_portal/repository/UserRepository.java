package com.hari.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.job_portal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
       boolean existsByEmail(String email);
}
