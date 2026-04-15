package com.hospital.hospital_managment.common.master.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StateResponse {
    private Long id;
    private String name;
    private String stateCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CountryResponse country;
}
