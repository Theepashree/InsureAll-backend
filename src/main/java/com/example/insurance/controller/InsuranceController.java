package com.example.insurance.controller;

import com.example.insurance.model.Insurance;
import com.example.insurance.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<Insurance> addInsurance(@RequestBody Insurance insurance) {
        Insurance savedInsurance = insuranceService.addInsurance(insurance);
        return ResponseEntity.status(201).body(savedInsurance);
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurance() {
        List<Insurance> insuranceList = insuranceService.getAllInsurance();
        return ResponseEntity.ok(insuranceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int id) {
        Optional<Insurance> insurance = insuranceService.getInsuranceById(id);
        return insurance.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable int id, @RequestBody Insurance insuranceDetails) {
        Insurance updatedInsurance = insuranceService.updateInsurance(id, insuranceDetails);
        return ResponseEntity.ok(updatedInsurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable int id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }
}
