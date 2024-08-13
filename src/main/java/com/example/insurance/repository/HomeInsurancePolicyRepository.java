package com.example.insurance.repository;

import com.example.insurance.model.HomeInsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeInsurancePolicyRepository extends JpaRepository<HomeInsurancePolicy, Long> {
}

