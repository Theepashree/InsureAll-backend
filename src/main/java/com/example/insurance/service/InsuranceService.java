package com.example.insurance.service;

import com.example.insurance.model.Insurance;
import com.example.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceById(int planId) {
        return insuranceRepository.findById(planId);
    }

    public Insurance updateInsurance(int planId, Insurance insuranceDetails) {
        Insurance insurance = insuranceRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Insurance plan not found"));
        insurance.setPlanType(insuranceDetails.getPlanType());
        return insuranceRepository.save(insurance);
    }

    public void deleteInsurance(int planId) {
        insuranceRepository.deleteById(planId);
    }
}
