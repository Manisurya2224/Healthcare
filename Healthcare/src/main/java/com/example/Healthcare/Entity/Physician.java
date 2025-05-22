package com.example.Healthcare.Entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @OneToMany(mappedBy = "physician")
    private List<PatientEntity> patients;
}
