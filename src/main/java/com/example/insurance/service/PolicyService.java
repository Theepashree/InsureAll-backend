package com.example.insurance.service;

import com.example.insurance.model.Policy;
import com.example.insurance.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy addPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public List<Policy> getPoliciesByPlanType(String planType) {
        return policyRepository.findByPlanType(planType);
    }



    public Optional<Policy> getPolicyById(Long policyId) {
        return policyRepository.findById(policyId);
    }

    public Policy updatePolicy(Long policyId, Policy policyDetails) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new RuntimeException("Policy not found"));
        policy.setPolicyName(policyDetails.getPolicyName());
        policy.setPlanType(policyDetails.getPlanType());
        policy.setDescription(policyDetails.getDescription());
        policy.setPeriod(policyDetails.getPeriod());
        return policyRepository.save(policy);
    }

    public void deletePolicy(Long policyId) {
        policyRepository.deleteById(policyId);
    }
}

