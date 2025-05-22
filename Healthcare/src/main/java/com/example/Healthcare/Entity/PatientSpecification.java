package com.example.Healthcare.Entity;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientSpecification {
    public static Specification<PatientEntity> withFilters(String name, Boolean active, LocalDate lastVisit) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (active != null) {
                predicate = cb.and(predicate, cb.equal(root.get("active"), active));
            }
            if (lastVisit != null) {
                predicate = cb.and(predicate, cb.equal(root.get("lastVisitDate"), lastVisit));
            }
            return predicate;
        };
    }
}
