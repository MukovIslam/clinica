package com.clinic.appointments_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppointmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsServiceApplication.class, args);
	}

}
