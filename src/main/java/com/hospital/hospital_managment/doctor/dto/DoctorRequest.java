package com.hospital.hospital_managment.doctor.dto;

import com.hospital.hospital_managment.Auth.dto.UserDetailRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorRequest {
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address1;
    private String address2;
    private Integer cityId;
    private Integer stateId;
    private Integer countryId;
    private String description;
    private String specialization;
    private Integer experienceYears = 0;
    private Float consultationFee = 0f;
}
