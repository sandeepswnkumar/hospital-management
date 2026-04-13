package com.hospital.hospital_managment.appointment.repository;


import com.hospital.hospital_managment.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, BigInteger> {
}
