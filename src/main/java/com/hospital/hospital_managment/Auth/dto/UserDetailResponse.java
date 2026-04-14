package com.hospital.hospital_managment.Auth.dto;

import com.hospital.hospital_managment.common.master.model.City;
import com.hospital.hospital_managment.common.master.model.Country;
import com.hospital.hospital_managment.common.master.model.State;
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
    private City city;
    private State state;
    private Country country;
}
