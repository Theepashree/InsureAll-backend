package com.example.insurance.controller;

import com.example.insurance.model.HealthInsurancePolicy;
import com.example.insurance.service.HealthInsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/health-insurance")
public class HealthInsurancePolicyController {

    @Autowired
    private HealthInsurancePolicyService service;

    @GetMapping
    public List<HealthInsurancePolicy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/{id}")
    public Optional<HealthInsurancePolicy> getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PostMapping
    public HealthInsurancePolicy createPolicy(@RequestBody HealthInsurancePolicy policy) {
        return service.createPolicy(policy);
    }

    @PutMapping("/{id}")
    public HealthInsurancePolicy updatePolicy(@PathVariable Long id, @RequestBody HealthInsurancePolicy policyDetails) {
        return service.updatePolicy(id, policyDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        service.deletePolicy(id);
    }
}

