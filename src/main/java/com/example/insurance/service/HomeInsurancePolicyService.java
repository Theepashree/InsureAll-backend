package com.example.insurance.service;

import com.example.insurance.model.HomeInsurancePolicy;
import com.example.insurance.repository.HomeInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeInsurancePolicyService {

    @Autowired
    private HomeInsurancePolicyRepository repository;

    public List<HomeInsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }

    public Optional<HomeInsurancePolicy> getPolicyById(Long id) {
        return repository.findById(id);
    }

    public HomeInsurancePolicy createPolicy(HomeInsurancePolicy policy) {
        return repository.save(policy);
    }

    public HomeInsurancePolicy updatePolicy(Long id, HomeInsurancePolicy policyDetails) {
        HomeInsurancePolicy policy = repository.findById(id).orElseThrow();
        policy.setPropertyAddress(policyDetails.getPropertyAddress());
        policy.setYearBuilt(policyDetails.getYearBuilt());
        policy.setResidenceType(policyDetails.getResidenceType());
        policy.setSquareFootage(policyDetails.getSquareFootage());
        policy.setSecuritySystems(policyDetails.getSecuritySystems());
        return repository.save(policy);
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }
}

