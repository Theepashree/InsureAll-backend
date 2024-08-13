package com.example.insurance.controller;

import com.example.insurance.model.VehicleInsurancePolicy;
import com.example.insurance.service.VehicleInsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-insurance")
public class VehicleInsurancePolicyController {

    @Autowired
    private VehicleInsurancePolicyService service;

    @GetMapping
    public List<VehicleInsurancePolicy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/{id}")
    public Optional<VehicleInsurancePolicy> getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PostMapping
    public VehicleInsurancePolicy createPolicy(@RequestBody VehicleInsurancePolicy policy) {
        return service.createPolicy(policy);
    }

    @PutMapping("/{id}")
    public VehicleInsurancePolicy updatePolicy(@PathVariable Long id, @RequestBody VehicleInsurancePolicy policyDetails) {
        return service.updatePolicy(id, policyDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        service.deletePolicy(id);
    }
}

