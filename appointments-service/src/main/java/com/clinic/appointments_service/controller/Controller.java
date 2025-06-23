package com.clinic.appointments_service.controller;

import com.clinic.appointments_service.entity.Appointment;
import com.clinic.appointments_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class Controller {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
        return  ResponseEntity.ok(service.create(appointment));
    }

    @GetMapping("/patient/{id}")
    public  ResponseEntity<List<Appointment>> getByPatient(@PathVariable Long id){
        return ResponseEntity.ok(service.getByPatientId(id));
    }

    @GetMapping("/www")
    public ResponseEntity<String> privet(){
        return ResponseEntity.ok("hello");
    }

}
