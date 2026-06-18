package com.hari.job_portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String location;
    private String website;
}
