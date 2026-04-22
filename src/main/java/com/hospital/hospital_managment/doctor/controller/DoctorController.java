package com.hospital.hospital_managment.doctor.controller;


import com.hospital.hospital_managment.common.utils.ApiResponse;
import com.hospital.hospital_managment.common.utils.ResponseUtil;
import com.hospital.hospital_managment.doctor.dto.DoctorRequest;
import com.hospital.hospital_managment.doctor.dto.DoctorResponse;
import com.hospital.hospital_managment.doctor.model.Doctor;
import com.hospital.hospital_managment.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(@RequestBody DoctorRequest doctorRequest){
        return ResponseUtil.success(doctorService.createDoctor(doctorRequest), "Doctor Created Successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAllDoctor(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String sortBy,
            @RequestParam(defaultValue = "") String sortDir,
            @RequestParam(defaultValue = "") String search
    ){
        Page<DoctorResponse> doctorResponses = doctorService.getAllDoctor(page, size, sortBy, sortDir, search);
        return ResponseUtil.successList(doctorResponses, "Doctors fetched Successfully");

    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor(@PathVariable BigInteger doctorId,  @RequestBody DoctorRequest doctorRequest){
        return ResponseUtil.success(doctorService.updateDoctor(doctorId, doctorRequest), "Doctor Updated Successfully");
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(@PathVariable BigInteger doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseUtil.success(null, "Doctor Deleted Successfully");
    }


}
