package com.clinic.doctors_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final  DoctorRepository repository;

    public List<Doctor> getDoctors() {
        return repository.findAll();
    }

    public Optional<Doctor> getDoctor(long id){
        return repository.findById(id);
    }
    public Doctor createDoctor(Doctor doctor) {
        return repository.save(doctor);
    }
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = repository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setSpecialty(doctorDetails.getSpecialty());
        doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
        return repository.save(doctor);
    }
    public void deleteDoctor (long id){
        repository.deleteById(id);
    }

}
