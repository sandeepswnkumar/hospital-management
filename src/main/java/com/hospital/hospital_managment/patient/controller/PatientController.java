package com.hospital.hospital_managment.patient.controller;

import com.hospital.hospital_managment.common.utils.ApiResponse;
import com.hospital.hospital_managment.common.utils.ResponseUtil;
import com.hospital.hospital_managment.patient.dto.PatientCreateRequest;
import com.hospital.hospital_managment.patient.dto.PatientResponse;
import com.hospital.hospital_managment.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponse>> createPatient(@RequestBody PatientCreateRequest patientCreateRequest){
        return ResponseUtil.success(patientService.createPatient(patientCreateRequest), "Patient Created Successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponse>>> getAllPatients(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "") String sortBy,
            @RequestParam(defaultValue = "") String sortDir
    ){
        return ResponseUtil.successList(patientService.getAllPatient(page, limit,sortBy, sortDir), "Patient fetched successfully");
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PatientResponse>> updatePatient(@PathVariable Long patientId, @RequestBody PatientCreateRequest patientCreateRequest){
        return ResponseUtil.success(patientService.updatePatient(patientId, patientCreateRequest), "Patient Updated Successfully");
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deletePatient(Long patientId){
        patientService.deletePatient(patientId);
        return ResponseUtil.success(null, "Patient Deleted Successfully");
    }




}
