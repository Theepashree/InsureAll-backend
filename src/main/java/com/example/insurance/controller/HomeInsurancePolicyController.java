package com.example.insurance.controller;

import com.example.insurance.model.HomeInsurancePolicy;
import com.example.insurance.service.HomeInsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/home-insurance")
public class HomeInsurancePolicyController {

    @Autowired
    private HomeInsurancePolicyService service;

    @GetMapping
    public List<HomeInsurancePolicy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/{id}")
    public Optional<HomeInsurancePolicy> getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PostMapping
    public HomeInsurancePolicy createPolicy(@RequestBody HomeInsurancePolicy policy) {
        return service.createPolicy(policy);
    }

    @PutMapping("/{id}")
    public HomeInsurancePolicy updatePolicy(@PathVariable Long id, @RequestBody HomeInsurancePolicy policyDetails) {
        return service.updatePolicy(id, policyDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        service.deletePolicy(id);
    }
}

