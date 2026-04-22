package com.hospital.hospital_managment.doctor.service;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.dto.UserDetailRequest;
import com.hospital.hospital_managment.Auth.dto.UserResponse;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.model.UserDetails;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.Auth.service.UserService;
import com.hospital.hospital_managment.common.utils.Helper;
import com.hospital.hospital_managment.doctor.dto.DoctorRequest;
import com.hospital.hospital_managment.doctor.dto.DoctorResponse;
import com.hospital.hospital_managment.doctor.model.Doctor;
import com.hospital.hospital_managment.doctor.repository.DoctorRepository;
import jakarta.persistence.criteria.Join;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;
    private final Helper helper;
    private final UserRepository userRepository;

    @Transactional
    public DoctorResponse createDoctor(DoctorRequest doctorRequest){
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        UserDetailRequest userDetailRequest = getUserDetailRequest(doctorRequest);
        userCreateRequest.setEmail(doctorRequest.getEmail());
        userCreateRequest.setPassword(helper.generateRandomPassword());
        userCreateRequest.setUserDetailRequest(userDetailRequest);
        UserResponse userResponse = userService.createUser(userCreateRequest);
        User user = userRepository.findById(userResponse.getId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setDescription(doctorRequest.getDescription());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setConsultationFee(doctorRequest.getConsultationFee());
        doctor.setExperienceYears(doctorRequest.getExperienceYears());
        return mapToDoctorResponse(doctorRepository.save(doctor));
    }

    @Transactional
    public DoctorResponse updateDoctor(BigInteger doctorId, DoctorRequest doctorRequest){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor Not Found"));
        User user = doctor.getUser();
        UserDetails userDetails = getUserDetails(doctorRequest, user);
        user.setUserDetails(userDetails);
        doctor.setConsultationFee(doctorRequest.getConsultationFee());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setExperienceYears(doctorRequest.getExperienceYears());
        doctor.setUser(user);
        return mapToDoctorResponse(doctorRepository.save(doctor));
    }

    private static UserDetails getUserDetails(DoctorRequest doctorRequest, User user) {
        UserDetails userDetails = user.getUserDetails();
        userDetails.setAddress1(doctorRequest.getAddress1());
        userDetails.setAddress2(doctorRequest.getAddress2());
        userDetails.setFirstName(doctorRequest.getFirstName());
        userDetails.setMiddleName(doctorRequest.getMiddleName());
        userDetails.setLastName(doctorRequest.getLastName());
        userDetails.setGender(doctorRequest.getGender());
        userDetails.setDateOfBirth(doctorRequest.getDateOfBirth());
        return userDetails;
    }


    private static UserDetailRequest getUserDetailRequest(DoctorRequest doctorRequest) {
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setFirstName(doctorRequest.getFirstName());
        userDetailRequest.setMiddleName(doctorRequest.getMiddleName());
        userDetailRequest.setLastName(doctorRequest.getLastName());
        userDetailRequest.setGender(doctorRequest.getGender());
        userDetailRequest.setAddress1(doctorRequest.getAddress1());
        userDetailRequest.setAddress2(doctorRequest.getAddress2());
        userDetailRequest.setCityId(doctorRequest.getCityId());
        userDetailRequest.setStateId(doctorRequest.getStateId());
        userDetailRequest.setCountryId(doctorRequest.getCountryId());
        return userDetailRequest;
    }


    public DoctorResponse mapToDoctorResponse(Doctor doctor){
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setId(doctor.getId());
        doctorResponse.setUserResponse(userService.mapToUserResponseDto(doctor.getUser()));
        doctorResponse.setSpecialization(doctor.getSpecialization());
        doctorResponse.setDescription(doctor.getDescription());
        doctorResponse.setExperienceYears(doctor.getExperienceYears());
        doctorResponse.setConsultationFee(doctor.getConsultationFee());
        return doctorResponse;
    }


    public Page<DoctorResponse> getAllDoctor(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search
    ){
        int currentPage = page - 1;

        Sort sort = null;
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "asc".equalsIgnoreCase(sortDir)
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
        }

        Pageable pageable = (sort != null)
                ? PageRequest.of(currentPage, size, sort)
                : PageRequest.of(currentPage, size);

        Specification<Doctor> specification = (root, query, cb) -> {

            if (search == null || search.trim().isEmpty()) {
                return cb.conjunction();
            }

            String searchText = "%" + search.trim().toLowerCase() + "%";

            Join<Object, Object> userJoin = root.join("user");
            Join<Object, Object> detailsJoin = userJoin.join("userDetails");

            return cb.or(
                    cb.like(cb.lower(detailsJoin.get("firstName")), searchText),
                    cb.like(cb.lower(detailsJoin.get("middleName")), searchText),
                    cb.like(cb.lower(detailsJoin.get("lastName")), searchText)
            );
        };

        Page<Doctor> doctors = doctorRepository.findAll(specification, pageable);

        return doctors.map(this::mapToDoctorResponse);
    }


    @Transactional
    public void deleteDoctor(BigInteger doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor Not Found"));
        doctorRepository.delete(doctor);

    }


}
