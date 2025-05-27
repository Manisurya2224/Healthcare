package com.example.Healthcare.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private LocalDateTime timeStamps;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private String path;
}

