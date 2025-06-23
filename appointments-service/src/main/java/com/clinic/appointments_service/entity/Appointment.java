package com.clinic.appointments_service.entity;


import com.clinic.appointments_service.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId; // Только ID пациента (данные в другом сервисе)
    private Long doctorId;  // Аналогично

    @OneToOne
    @JoinColumn(name = "slot_id")
    private ScheduleSlot slot;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private AppointmentMetadata metadata;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

}
