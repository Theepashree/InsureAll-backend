package com.example.insurance.controller;

import com.example.insurance.model.AppliedPolicy;
import com.example.insurance.service.AppliedPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appliedPolicies")
public class AppliedPolicyController {

    @Autowired
    private AppliedPolicyService appliedPolicyService;

    @GetMapping("/username")
    public ResponseEntity<List<AppliedPolicy>> getAppliedPolicies(@RequestParam(required = false) String userName) {
        List<AppliedPolicy> policies;
        if (userName != null) {
            policies = appliedPolicyService.getAppliedPoliciesByUserName(userName);
        } else {
            policies = appliedPolicyService.getAllAppliedPolicies();
        }
        return ResponseEntity.ok(policies);
    }


    @PostMapping
    public ResponseEntity<AppliedPolicy> addAppliedPolicy(
            @RequestParam("policyName") String policyName,
            @RequestParam("planType") String planType,
            @RequestParam("customerName") String customerName,
            @RequestParam("userName") String userName,
            @RequestParam("term") int term,
            @RequestParam("period") int period,
            @RequestParam("coverageAmount") long coverageAmount
    ) {
        AppliedPolicy appliedPolicy = new AppliedPolicy();
        appliedPolicy.setPolicyName(policyName);
        appliedPolicy.setPlanType(planType);
        appliedPolicy.setCustomerName(customerName);
        appliedPolicy.setUserName(userName); 
        appliedPolicy.setTerm(term);
        appliedPolicy.setPeriod(period);
        appliedPolicy.setCoverageAmount(coverageAmount);
        AppliedPolicy savedAppliedPolicy = appliedPolicyService.addAppliedPolicy(appliedPolicy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppliedPolicy);
    }

    @PostMapping("/documents")
    public ResponseEntity<String> uploadDocuments(
            @RequestParam("incomeCertificate") MultipartFile incomeCertificate,
            @RequestParam(value = "selfCancelledCheque", required = false) MultipartFile selfCancelledCheque,
            @RequestParam(value = "communicationAddressProof", required = false) MultipartFile communicationAddressProof,
            @RequestParam(value = "birthCertificate", required = false) MultipartFile birthCertificate,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "signature", required = false) MultipartFile signature
    ) {
        try {
            AppliedPolicy lastAppliedPolicy = appliedPolicyService.getLastAppliedPolicy();
            appliedPolicyService.saveDocuments(lastAppliedPolicy.getAppliedPolicyId(), incomeCertificate, selfCancelledCheque, communicationAddressProof, birthCertificate, photo, signature);
            return ResponseEntity.status(HttpStatus.CREATED).body("Documents uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload documents");
        }
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<Map<String, String>> getDocuments(@PathVariable Long id) {
        Map<String, String> documents = appliedPolicyService.getDocuments(id);
        return ResponseEntity.ok(documents);
    }

    @GetMapping
    public ResponseEntity<List<AppliedPolicy>> getAllAppliedPolicies() {
        List<AppliedPolicy> appliedPolicyList = appliedPolicyService.getAllAppliedPolicies();
        return ResponseEntity.ok(appliedPolicyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppliedPolicy> getAppliedPolicyById(@PathVariable Long id) {
        return appliedPolicyService.getAppliedPolicyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<AppliedPolicy>> getApprovedPolicies() {
        List<AppliedPolicy> approvedPolicies = appliedPolicyService.getApprovedPolicies();
        return ResponseEntity.ok(approvedPolicies);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<AppliedPolicy>> getPendingPolicies() {
        List<AppliedPolicy> pendingPolicies = appliedPolicyService.getPendingPolicies();
        return ResponseEntity.ok(pendingPolicies);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approvePolicy(@PathVariable Long id) {
        appliedPolicyService.updatePolicyStatus(id, "approved");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectPolicy(@PathVariable Long id) {
        appliedPolicyService.updatePolicyStatus(id, "rejected");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppliedPolicy(@PathVariable Long id) {
        appliedPolicyService.deleteAppliedPolicy(id);
        return ResponseEntity.noContent().build();
    }
}



