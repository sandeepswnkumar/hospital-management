package com.hospital.hospital_managment.patient.service;


import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.patient.dto.PatientCreateRequest;
import com.hospital.hospital_managment.patient.dto.PatientResponse;
import com.hospital.hospital_managment.patient.model.Patient;
import com.hospital.hospital_managment.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientResponse createPatient(PatientCreateRequest patientCreateRequest){
        User user = userRepository.findById(patientCreateRequest.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found"))
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFirstName();
    }
}
