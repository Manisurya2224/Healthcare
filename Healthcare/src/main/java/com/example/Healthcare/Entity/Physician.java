package com.example.Healthcare.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "physician")
    private List<PatientEntity> patients;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
