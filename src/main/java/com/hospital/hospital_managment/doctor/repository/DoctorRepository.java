package com.hospital.hospital_managment.doctor.repository;

import com.hospital.hospital_managment.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, BigInteger> {
}
