package com.clinic.patients_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository <Patient , Long> {
}
