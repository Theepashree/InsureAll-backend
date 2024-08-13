package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.insurance.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
