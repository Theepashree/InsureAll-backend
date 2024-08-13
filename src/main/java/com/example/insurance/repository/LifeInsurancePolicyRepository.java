package com.example.insurance.repository;

import com.example.insurance.model.LifeInsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeInsurancePolicyRepository extends JpaRepository<LifeInsurancePolicy, Long> {
}

