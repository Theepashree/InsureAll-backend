package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vehicle_insurance_policy")
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private int currentMileage;
    private String previousClaims;
    private String usageType;
    private String usageFrequency;
}

