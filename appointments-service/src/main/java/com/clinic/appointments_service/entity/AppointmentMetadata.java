package com.clinic.appointments_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "appointment_metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Например: "initial", "follow-up"
    private String source; // откуда запись пришла (веб, приложение и т.п.)
    private String notes;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}