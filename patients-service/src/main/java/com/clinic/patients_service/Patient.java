package com.clinic.patients_service;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String phone;

}
