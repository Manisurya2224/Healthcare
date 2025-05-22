package com.example.Healthcare.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private boolean active;
    private LocalDate lastVisitDate;

    @ManyToOne
    @JoinColumn(name = "physician_id")
    private Physician physician;
}
