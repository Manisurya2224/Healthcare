package com.example.Healthcare.response;


import com.example.Healthcare.Entity.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}