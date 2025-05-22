package com.example.Healthcare.Repo;

import com.example.Healthcare.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity> {
}
