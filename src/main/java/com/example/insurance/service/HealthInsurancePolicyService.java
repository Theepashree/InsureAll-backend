package com.example.insurance.service;

import com.example.insurance.model.HealthInsurancePolicy;
import com.example.insurance.repository.HealthInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthInsurancePolicyService {

    @Autowired
    private HealthInsurancePolicyRepository repository;

    public List<HealthInsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }

    public Optional<HealthInsurancePolicy> getPolicyById(Long id) {
        return repository.findById(id);
    }

    public HealthInsurancePolicy createPolicy(HealthInsurancePolicy policy) {
        return repository.save(policy);
    }

    public HealthInsurancePolicy updatePolicy(Long id, HealthInsurancePolicy policyDetails) {
        HealthInsurancePolicy policy = repository.findById(id).orElseThrow();
        policy.setHeightWeight(policyDetails.getHeightWeight());
        policy.setExistingConditions(policyDetails.getExistingConditions());
        policy.setPrimaryPhysician(policyDetails.getPrimaryPhysician());
        policy.setMedications(policyDetails.getMedications());
        policy.setHospitalPreferences(policyDetails.getHospitalPreferences());
        return repository.save(policy);
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }
}

