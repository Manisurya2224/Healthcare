package com.example.Healthcare.Repo;

import com.example.Healthcare.Entity.PatientEntity;
import com.example.Healthcare.Entity.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>{
    List<PatientEntity> findByPhysician(Physician physician);
}
