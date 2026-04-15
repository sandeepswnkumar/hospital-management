package com.hospital.hospital_managment.common.master.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityResponse {
    private Long id;
    private String name;
    private Float latitude;
    private Float longitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StateResponse state;
}
