package com.clinic.appointments_service.service;

import com.clinic.appointments_service.repository.AppointmentRepository;
import com.clinic.appointments_service.entity.ScheduleSlot;
import com.clinic.appointments_service.repository.ScheduleSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceSlot {
    private final ScheduleSlotRepository scheduleSlotRepository;

    public ScheduleSlot create(ScheduleSlot slot) {
        return  scheduleSlotRepository.save(slot);
    }
    public void delete(long id) {
        scheduleSlotRepository.deleteById(id);
    }

    public List<ScheduleSlot> findAll() {
        return scheduleSlotRepository.findAll();
    }
    public ScheduleSlot findById(long id) {
        return scheduleSlotRepository.findById(id).orElse(null);
    }
    public List<ScheduleSlot> findAllFree(long id, LocalDate date){
        System.out.println(scheduleSlotRepository.findByDoctorIdAndAvailableTrue(id));
        return scheduleSlotRepository.findByDoctorIdAndAvailableTrue(id);
    }
    public String createSlotWeek(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(9,0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ScheduleSlot scheduleSlot = new ScheduleSlot();
                scheduleSlot.setDate(date.plusDays(i));
                scheduleSlot.setTime(time.plusHours(j));
                System.out.println(time.plusHours(j));
            scheduleSlotRepository.save(scheduleSlot);
            }
        }
        return " weekly schedule created ";
    }
    @Scheduled(fixedRate = 1000000)
    public void schedulled(){
        System.out.println("schedulled run");
    }

}
