package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.insurance.model.Policy;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByPlanType(String planType);
}




