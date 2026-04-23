package com.hospital.hospital_managment.patient.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class PatientResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
}
