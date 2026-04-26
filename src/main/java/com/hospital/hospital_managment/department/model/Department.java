package com.hospital.hospital_managment.department.model;


import com.hospital.hospital_managment.doctor.model.Doctor;
import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "head_doctor_id")
    private Doctor headDoctor;
}
