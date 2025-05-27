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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) { this.lastVisitDate = lastVisitDate; }

    public Physician getPhysician() { return physician; }
    public void setPhysician(Physician physician) { this.physician = physician; }
}
