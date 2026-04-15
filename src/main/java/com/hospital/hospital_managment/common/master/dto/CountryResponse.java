package com.hospital.hospital_managment.common.master.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CountryResponse {

    private Integer id;
    private String name;
    private String isoCode;
    private String phoneCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
