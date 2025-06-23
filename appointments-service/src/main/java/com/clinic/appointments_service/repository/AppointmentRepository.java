package com.clinic.appointments_service.repository;

import com.clinic.appointments_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatientId(long patientId);
 }
