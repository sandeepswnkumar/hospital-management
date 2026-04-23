package com.hospital.hospital_managment.patient.repository;

import com.hospital.hospital_managment.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
