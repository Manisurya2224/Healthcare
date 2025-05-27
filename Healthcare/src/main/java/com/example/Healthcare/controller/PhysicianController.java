package com.example.Healthcare.controller;

import com.example.Healthcare.Entity.PatientEntity;
import com.example.Healthcare.Entity.Physician;
import com.example.Healthcare.Service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/physician")
@CrossOrigin
public class PhysicianController {

    @Autowired
    private PhysicianService physicianService;
    @GetMapping("/patients")
    public ResponseEntity<List<PatientEntity>> getPatientsForLoggedInPhysician() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User in controller: " + auth.getName());
        System.out.println("Authorities in controller: " + auth.getAuthorities());

        Physician physician = physicianService.getLoggedInPhysician();

        if (physician == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(physician.getPatients());
    }
}
