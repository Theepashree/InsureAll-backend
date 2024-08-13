package com.example.insurance.repository;

import com.example.insurance.model.HealthInsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInsurancePolicyRepository extends JpaRepository<HealthInsurancePolicy, Long> {
}

