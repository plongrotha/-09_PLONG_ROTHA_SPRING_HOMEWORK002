package com.homework002.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime timestamp;
}

