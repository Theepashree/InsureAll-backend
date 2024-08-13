package com.example.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int zip;

    @Column(nullable = false)
    private long mobileNumber;

    @Column(name="DOB1")
    private java.sql.Date DOB1;

    @Column(nullable = false)
    private String nomineeName;

    @Column(nullable = false)
    private String nomineeOccupation;

    @Column(nullable = false)
    private String nomineeAddress;

    @Column(nullable = false)
    private long nomineemobileNumber;

    @Column(name="nomineeDOB")
    private java.sql.Date nomineeDOB;
    @OneToOne
    @JoinColumn(name = "customerId", referencedColumnName = "userId")
    private User user;
    // Other fields, constructors, getters, setters, and relationships (if any) go here
}
