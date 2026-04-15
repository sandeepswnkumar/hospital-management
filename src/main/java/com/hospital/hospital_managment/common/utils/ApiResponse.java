package com.hospital.hospital_managment.common.utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    // Error handling
    private List<String> errors;

    // Pagination fields
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    private LocalDateTime timestamp;
}