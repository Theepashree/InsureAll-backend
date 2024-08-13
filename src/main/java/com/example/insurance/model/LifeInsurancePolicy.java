package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "life_insurance_policy")
@AllArgsConstructor
@NoArgsConstructor
public class LifeInsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String annualIncome;
    @Column
    private String healthHistory;
    @Column
    private String smokingStatus;
    @Column
    private String hobbiesLifestyle;
    @Column
    private String medicalExamDetails;
}

