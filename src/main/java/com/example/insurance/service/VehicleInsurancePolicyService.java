package com.example.insurance.service;

import com.example.insurance.model.VehicleInsurancePolicy;
import com.example.insurance.repository.VehicleInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleInsurancePolicyService {

    @Autowired
    private VehicleInsurancePolicyRepository repository;

    public List<VehicleInsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }

    public Optional<VehicleInsurancePolicy> getPolicyById(Long id) {
        return repository.findById(id);
    }

    public VehicleInsurancePolicy createPolicy(VehicleInsurancePolicy policy) {
        return repository.save(policy);
    }

    public VehicleInsurancePolicy updatePolicy(Long id, VehicleInsurancePolicy policyDetails) {
        VehicleInsurancePolicy policy = repository.findById(id).orElseThrow();
        policy.setVin(policyDetails.getVin());
        policy.setCurrentMileage(policyDetails.getCurrentMileage());
        policy.setPreviousClaims(policyDetails.getPreviousClaims());
        policy.setUsageType(policyDetails.getUsageType());
        policy.setUsageFrequency(policyDetails.getUsageFrequency());
        return repository.save(policy);
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }
}

