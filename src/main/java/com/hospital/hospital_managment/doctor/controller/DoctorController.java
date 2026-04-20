package com.hospital.hospital_managment.doctor.controller;


import com.hospital.hospital_managment.common.utils.ApiResponse;
import com.hospital.hospital_managment.common.utils.ResponseUtil;
import com.hospital.hospital_managment.doctor.dto.DoctorRequest;
import com.hospital.hospital_managment.doctor.dto.DoctorResponse;
import com.hospital.hospital_managment.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(@RequestBody DoctorRequest doctorRequest){
        return ResponseUtil.success(doctorService.createDoctor(doctorRequest), "Doctor Create Successfully");
    }

}
