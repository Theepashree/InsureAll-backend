package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "home_insurance_policy")
@AllArgsConstructor
@NoArgsConstructor
public class HomeInsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyAddress;
    private int yearBuilt;
    private String residenceType;
    private int squareFootage;
    private String securitySystems;
}

