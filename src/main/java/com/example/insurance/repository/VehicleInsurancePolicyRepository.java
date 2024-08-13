package com.example.insurance.repository;

import com.example.insurance.model.VehicleInsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleInsurancePolicyRepository extends JpaRepository<VehicleInsurancePolicy, Long> {
}

