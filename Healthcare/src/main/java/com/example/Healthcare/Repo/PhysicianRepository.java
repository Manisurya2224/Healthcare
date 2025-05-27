package com.example.Healthcare.Repo;
import com.example.Healthcare.Entity.Physician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Physician findByUser_Email(String email);

}
