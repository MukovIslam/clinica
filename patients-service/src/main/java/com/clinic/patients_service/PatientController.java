package com.clinic.patients_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")

public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<Patient> getAllDoctors (){
        return patientService.getAllPatients();
    }

    // Получить врача по ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getDoctorById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatient(id);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить нового врача
    @PostMapping
    public ResponseEntity<Patient> createDoctor(@RequestBody Patient patient) {

        Patient createdDoctor = patientService.createPatient(patient);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    // Удалить врача
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
