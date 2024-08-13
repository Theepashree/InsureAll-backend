package com.example.insurance.controller;

import com.example.insurance.model.LifeInsurancePolicy;
import com.example.insurance.service.LifeInsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/life-insurance")
public class LifeInsurancePolicyController {

    @Autowired
    private LifeInsurancePolicyService service;

    @GetMapping
    public List<LifeInsurancePolicy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/{id}")
    public Optional<LifeInsurancePolicy> getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PostMapping
    public LifeInsurancePolicy createPolicy(@RequestBody LifeInsurancePolicy policy) {
        System.out.println(policy);
        return service.createPolicy(policy);
    }

    @PutMapping("/{id}")
    public LifeInsurancePolicy updatePolicy(@PathVariable Long id, @RequestBody LifeInsurancePolicy policyDetails) {
        return service.updatePolicy(id, policyDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        service.deletePolicy(id);
    }
}

