package com.example.Healthcare.controller;

import com.example.Healthcare.Entity.PatientEntity;
import com.example.Healthcare.Entity.PatientSpecification;
import com.example.Healthcare.Repo.PatientRepository;
import com.example.Healthcare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<?> generate(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        System.out.println("userName++++++++++++: " + username);
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }


    @GetMapping
    public Page<PatientEntity> getPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastVisit,
            Pageable pageable,
            Authentication auth
    ) {
        // Extract username from token
        String physicianName = "physician1"; // set during JWT parsing

        // Build dynamic filter spec
        Specification<PatientEntity> spec = PatientSpecification.withFilters(name, active, lastVisit)
                .and((root, query, cb) -> cb.equal(root.get("physician").get("name"), physicianName));

        // Paginate the filtered result
        return patientRepository.findAll(spec, pageable);
    }
}
