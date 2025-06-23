package com.clinic.patients_service;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Repository repository ;

    public List<Patient> getAllPatients(){
        return  repository.findAll();
    }

    public Patient createPatient (Patient patient){
        Patient patient1 =  repository.save(patient);
        kafkaTemplate.send("patient-create-topic",String.valueOf(patient1.getId()));
        return patient1;
    }

    public Optional<Patient> getPatient (long id){
        return repository.findById(id);

    }

    public void deletePatient (long id){
        repository.deleteById(id);
        kafkaTemplate.send("patient-deleted-topic", String.valueOf(id));
    }

}
