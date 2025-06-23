package com.clinic.appointments_service.service;


import com.clinic.appointments_service.repository.AppointmentRepository;
import com.clinic.appointments_service.AppointmentStatus;
import com.clinic.appointments_service.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private  final AppointmentRepository repository;

    public Appointment create (Appointment appointment){
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        return repository.save(appointment);
    }

    public List<Appointment> getByPatientId(Long patientId){
        return repository.findByPatientId(patientId);
    }

    public void deleteAllPatientId(Long id){
        List<Appointment> appointments = repository.findByPatientId(id);
        repository.deleteAll(appointments);
    }
}
