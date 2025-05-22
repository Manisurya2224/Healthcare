package com.example.Healthcare.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Example in-memory users. Replace with DB lookup if needed.
    private static final Map<String, String> users = Map.of(
            "physician1", "ROLE_PHYSICIAN",
            "patient1", "ROLE_PATIENT"
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = users.get(username);
        if (role == null) throw new UsernameNotFoundException("User not found");

        return User.builder()
                .username(username)
                .password("") // password validation skipped for demo
                .roles(role.replace("ROLE_", ""))
                .build();
    }
}