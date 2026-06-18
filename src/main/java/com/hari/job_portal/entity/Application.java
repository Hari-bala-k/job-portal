package com.hari.job_portal.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "applications")
public class Application {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)    
     private Long id;
     private String status;
     private  LocalDate applicationDate;
     
     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;
     
     @ManyToOne
     @JoinColumn(name = "job_id")
     private Job job;
}
