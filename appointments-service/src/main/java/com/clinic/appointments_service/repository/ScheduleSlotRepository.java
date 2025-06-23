package com.clinic.appointments_service.repository;

import com.clinic.appointments_service.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {

    List<ScheduleSlot> findByDoctorIdAndAvailableTrue(Long doctorId);

    // Можно добавить фильтр по дате, если нужно:
    List<ScheduleSlot> findByDoctorIdAndAvailableTrueAndDate(Long doctorId, LocalDate date);
}
