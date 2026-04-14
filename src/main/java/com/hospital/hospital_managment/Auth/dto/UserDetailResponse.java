package com.hospital.hospital_managment.Auth.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address1;
    private String address2;
    private Integer city;
    private Integer state;
    private Integer country;
}
