package com.hospital.hospital_managment.Auth.controller;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.dto.UserResponse;
import com.hospital.hospital_managment.Auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    UserService userService;

    @PostMapping("/")
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }


    @GetMapping("/")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
