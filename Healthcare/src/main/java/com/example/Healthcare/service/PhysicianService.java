package com.example.Healthcare.Service;

import com.example.Healthcare.Entity.Physician;
import com.example.Healthcare.Repo.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    public Physician getLoggedInPhysician() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return physicianRepository.findByUser_Email(email);
    }
}
