package com.hospital.hospital_managment.appointment.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer patient_id;

    private Integer doctor_id;

    private LocalDateTime appointment_time;

    private String status;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime created_at =  LocalDateTime.now();
}
