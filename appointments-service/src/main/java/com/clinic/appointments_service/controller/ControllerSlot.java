package com.clinic.appointments_service.controller;

import com.clinic.appointments_service.entity.Appointment;
import com.clinic.appointments_service.entity.ScheduleSlot;
import com.clinic.appointments_service.service.ServiceSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments/slot")
@RequiredArgsConstructor
public class ControllerSlot {

    private final ServiceSlot service;

    @PostMapping
    public ResponseEntity<ScheduleSlot> create(@RequestBody ScheduleSlot slot) {
        return ResponseEntity.ok(service.create(slot));
    }

    @DeleteMapping
    public void delete(long id) {
        service.delete(id);
    }

    @GetMapping
    public List<ScheduleSlot> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<ScheduleSlot> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/free")
    public List<ScheduleSlot> findAllFree(long id, LocalDate date) {
        return service.findAllFree(id, date);
    }

    @GetMapping("/generate-week")
    public ResponseEntity<String> createSlotWeek (){
        return ResponseEntity.ok( service.createSlotWeek());
    }
}
