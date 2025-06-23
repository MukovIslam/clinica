package com.clinic.appointments_service.kafka;

import com.clinic.appointments_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceKafka {
    private final AppointmentService service;

    @KafkaListener(topics = "patient-deleted-topic", groupId = "appointments-group")
    public void handlePatientDeletion(String patientIdStr) {
        Long patientId = Long.parseLong(patientIdStr);

        // Удаляем все записи о приёмах с этим patientId
        service.deleteAllPatientId(patientId);
        System.out.println(" delete patient id : " + patientId);

    }
}
