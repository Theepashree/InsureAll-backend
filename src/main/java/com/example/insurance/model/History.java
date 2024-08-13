package com.example.insurance.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tenure;
    private long coverageAmount;
    private long termAmount;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date emailSentDate;
}

