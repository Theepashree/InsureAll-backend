package com.example.insurance.service;

import com.example.insurance.model.AppliedPolicy;
import com.example.insurance.repository.AppliedPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppliedPolicyService {

    @Autowired
    private AppliedPolicyRepository appliedPolicyRepository;

public AppliedPolicy addAppliedPolicy(AppliedPolicy appliedPolicy) {
    appliedPolicy.setCurrentDate(new java.sql.Date(System.currentTimeMillis()));
    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
    appliedPolicy.setNextPaymentDate(java.sql.Date.valueOf(currentDate.toLocalDate().plusMonths(appliedPolicy.getTerm())));

    if (appliedPolicy.getTerm() == 6) {
        appliedPolicy.setTermAmount((appliedPolicy.getCoverageAmount() / appliedPolicy.getPeriod()) * 2);
    } else if (appliedPolicy.getTerm() == 12) {
        appliedPolicy.setTermAmount(appliedPolicy.getCoverageAmount() / appliedPolicy.getPeriod());
    }

    appliedPolicy.setStatus("inprogress");

    return appliedPolicyRepository.save(appliedPolicy);
}

    public List<AppliedPolicy> getAppliedPoliciesByUserName(String userName) {
        return appliedPolicyRepository.findByUserName(userName);
    }


    public List<AppliedPolicy> getAllAppliedPolicies() {
        return appliedPolicyRepository.findAll();
    }

    public Optional<AppliedPolicy> getAppliedPolicyById(Long appliedPolicyId) {
        return appliedPolicyRepository.findById(appliedPolicyId);
    }

    public AppliedPolicy getLastAppliedPolicy() {
        return appliedPolicyRepository.findFirstByOrderByAppliedPolicyIdDesc();
    }

    public void saveDocuments(Long appliedPolicyId, MultipartFile incomeCertificate, MultipartFile selfCancelledCheque,
                              MultipartFile communicationAddressProof, MultipartFile birthCertificate,
                              MultipartFile photo, MultipartFile signature) throws IOException {

        Optional<AppliedPolicy> optionalAppliedPolicy = appliedPolicyRepository.findById(appliedPolicyId);
        if (optionalAppliedPolicy.isPresent()) {
            AppliedPolicy appliedPolicy = optionalAppliedPolicy.get();

            if (incomeCertificate != null) {
                appliedPolicy.setIncomeCertificate(Base64.getEncoder().encodeToString(incomeCertificate.getBytes()));
            }

            if (selfCancelledCheque != null) {
                appliedPolicy.setSelfCancelledCheque(Base64.getEncoder().encodeToString(selfCancelledCheque.getBytes()));
            }

            if (communicationAddressProof != null) {
                appliedPolicy.setCommunicationAddressProof(Base64.getEncoder().encodeToString(communicationAddressProof.getBytes()));
            }

            if (birthCertificate != null) {
                appliedPolicy.setBirthCertificate(Base64.getEncoder().encodeToString(birthCertificate.getBytes()));
            }

            if (photo != null) {
                appliedPolicy.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
            }

            if (signature != null) {
                appliedPolicy.setSignature(Base64.getEncoder().encodeToString(signature.getBytes()));
            }

            appliedPolicyRepository.save(appliedPolicy);
        }
    }

    public Map<String, String> getDocuments(Long id) {
        Map<String, String> documents = new HashMap<>();
        Optional<AppliedPolicy> optionalAppliedPolicy = appliedPolicyRepository.findById(id);
        if (optionalAppliedPolicy.isPresent()) {
            AppliedPolicy appliedPolicy = optionalAppliedPolicy.get();

            documents.put("incomeCertificate", appliedPolicy.getIncomeCertificate());
            documents.put("selfCancelledCheque", appliedPolicy.getSelfCancelledCheque());
            documents.put("communicationAddressProof", appliedPolicy.getCommunicationAddressProof());
            documents.put("birthCertificate", appliedPolicy.getBirthCertificate());
            documents.put("photo", appliedPolicy.getPhoto());
            documents.put("signature", appliedPolicy.getSignature());
        }
        return documents;
    }

    public void updatePolicyStatus(Long id, String status) {
        Optional<AppliedPolicy> optionalAppliedPolicy = appliedPolicyRepository.findById(id);
        if (optionalAppliedPolicy.isPresent()) {
            AppliedPolicy appliedPolicy = optionalAppliedPolicy.get();
            appliedPolicy.setStatus(status);
            appliedPolicyRepository.save(appliedPolicy);
        }
    }

    public void deleteAppliedPolicy(Long id) {
        appliedPolicyRepository.deleteById(id);
    }

    public List<AppliedPolicy> getApprovedPolicies() {
        return appliedPolicyRepository.findByStatus("approved");
    }

    public List<AppliedPolicy> getPendingPolicies() {
        return appliedPolicyRepository.findByStatus("inprogress");
    }
}



