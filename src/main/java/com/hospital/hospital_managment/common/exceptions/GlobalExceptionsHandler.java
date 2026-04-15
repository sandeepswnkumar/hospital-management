package com.hospital.hospital_managment.common.exceptions;

import com.hospital.hospital_managment.common.utils.ApiResponse;
import com.hospital.hospital_managment.common.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(RuntimeException.class)
    public <T>ResponseEntity<ApiResponse<T>> handleRunTimeException(RuntimeException exception){
        return ResponseUtil.error(exception.getMessage(),  new ArrayList<>());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toString());
    }

    
    @ExceptionHandler(Exception.class)
    public <T>ResponseEntity<ApiResponse<T>> handleException(Exception exception){
       return ResponseUtil.error(exception.getMessage(), new ArrayList<>());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toString());
    }


}
