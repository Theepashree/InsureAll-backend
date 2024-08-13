package com.example.insurance.service;

import com.example.insurance.model.LifeInsurancePolicy;
import com.example.insurance.repository.LifeInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifeInsurancePolicyService {

    @Autowired
    private LifeInsurancePolicyRepository repository;

    public List<LifeInsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }

    public Optional<LifeInsurancePolicy> getPolicyById(Long id) {
        return repository.findById(id);
    }

    public LifeInsurancePolicy createPolicy(LifeInsurancePolicy policy) {
        return repository.save(policy);
    }

    public LifeInsurancePolicy updatePolicy(Long id, LifeInsurancePolicy policyDetails) {
        LifeInsurancePolicy policy = repository.findById(id).orElseThrow();
        policy.setAnnualIncome(policyDetails.getAnnualIncome());
        policy.setHealthHistory(policyDetails.getHealthHistory());
        policy.setSmokingStatus(policyDetails.getSmokingStatus());
        policy.setHobbiesLifestyle(policyDetails.getHobbiesLifestyle());
        policy.setMedicalExamDetails(policyDetails.getMedicalExamDetails());
        return repository.save(policy);
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }
}

