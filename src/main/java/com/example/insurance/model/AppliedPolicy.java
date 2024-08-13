package com.example.insurance.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "appliedPolicy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppliedPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appliedPolicyId;

    private String policyName;
    private String planType;
    private String customerName;
    private String userName;
    private int term;
    private int period;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date currentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextPaymentDate;
    private long coverageAmount;
    private long termAmount;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tenure;

    @Lob
    private String incomeCertificate;

    @Lob
    private String selfCancelledCheque;

    @Lob
    private String communicationAddressProof;

    @Lob
    private String birthCertificate;

    @Lob
    private String photo;

    @Lob
    private String signature;
}

