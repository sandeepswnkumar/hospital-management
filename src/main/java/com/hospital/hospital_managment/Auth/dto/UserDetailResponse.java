package com.hospital.hospital_managment.Auth.dto;

import com.hospital.hospital_managment.common.master.dto.CityResponse;
import com.hospital.hospital_managment.common.master.dto.CountryResponse;
import com.hospital.hospital_managment.common.master.dto.StateResponse;
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
    private CityResponse city;
    private StateResponse state;
    private CountryResponse country;
}
