package com.hospital.hospital_managment.department.model;


import com.hospital.hospital_managment.doctor.model.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Setter
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "head_doctor_id")
    private Doctor headDoctor;

    @ManyToMany
    @JoinTable(
            name = "doctor_departments",
            joinColumns = @JoinColumn(name =  "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();
}
