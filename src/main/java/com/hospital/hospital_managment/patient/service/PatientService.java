package com.hospital.hospital_managment.patient.service;


import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.patient.dto.PatientCreateRequest;
import com.hospital.hospital_managment.patient.dto.PatientResponse;
import com.hospital.hospital_managment.patient.model.Patient;
import com.hospital.hospital_managment.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Transactional
    public PatientResponse createPatient(PatientCreateRequest patientCreateRequest){
        User user = userRepository.findById(patientCreateRequest.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFirstName(patientCreateRequest.getFirstName());
        patient.setMiddleName(patientCreateRequest.getMiddleName());
        patient.setLastName(patientCreateRequest.getLastName());
        patient.setDateOfBirth(patientCreateRequest.getDateOfBirth());
        patient.setGender(patientCreateRequest.getGender());
        return mapToPatientResponse(patientRepository.save(patient));
    }


    public PatientResponse mapToPatientResponse(Patient patient){
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patient.getId());
        patientResponse.setFirstName(patient.getFirstName());
        patientResponse.setMiddleName(patient.getMiddleName());
        patientResponse.setLastName(patient.getLastName());
        patientResponse.setGender(patient.getGender());
        patientResponse.setDateOfBirth(patient.getDateOfBirth());
        return patientResponse;
    }

    public PatientResponse updatePatient(Long patientId, PatientCreateRequest patientCreateRequest){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient Not Found"));
        patient.setFirstName(patientCreateRequest.getFirstName());
        patient.setMiddleName(patientCreateRequest.getMiddleName());
        patient.setLastName(patientCreateRequest.getLastName());
        patient.setGender(patientCreateRequest.getGender());
        patient.setDateOfBirth(patientCreateRequest.getDateOfBirth());
        return mapToPatientResponse(patientRepository.save(patient));
    }

    public Page<PatientResponse> getAllPatient(int page, int limit, String sortBy, String sortDir){
        Sort sort = null;
        if(sortBy != null && !sortBy.isBlank()){
            sort = "asc".equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        }
        Pageable pageable = sort == null ? PageRequest.of(page, limit) : PageRequest.of(page, limit, sort);
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(this::mapToPatientResponse);
    }

    public void deletePatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("Patient Not Found"));
        patientRepository.delete(patient);
    }


}
