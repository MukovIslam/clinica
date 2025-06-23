package com.clinic.patients_service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic patientDeletedTopic() {
        return TopicBuilder.name("patient-deleted-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic patientCreatePatient() {
        return TopicBuilder.name("patient-create-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}