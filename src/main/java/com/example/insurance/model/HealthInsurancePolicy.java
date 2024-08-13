package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "health_insurance_policy")
@AllArgsConstructor
@NoArgsConstructor
public class HealthInsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heightWeight;
    private String existingConditions;
    private String primaryPhysician;
    private String medications;
    private String hospitalPreferences;
}

