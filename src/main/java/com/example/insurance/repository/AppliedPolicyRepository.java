package com.example.insurance.repository;

import com.example.insurance.model.AppliedPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppliedPolicyRepository extends JpaRepository<AppliedPolicy, Long> {
    List<AppliedPolicy> findByStatus(String status);
    List<AppliedPolicy> findByUserName(String userName);
    @Query(value = "SELECT * FROM appliedPolicy ORDER BY appliedPolicyId DESC LIMIT 1", nativeQuery = true)
    AppliedPolicy findFirstByOrderByAppliedPolicyIdDesc();

    @Query(value="SELECT * FROM appliedPolicy a WHERE a.nextPaymentDate =?",nativeQuery = true)
    List<AppliedPolicy> findByNextPaymentDate(@Param("nextPaymentDate") Date nextPaymentDate);

    @Query(value="SELECT * FROM appliedPolicy a WHERE a.tenure =?",nativeQuery = true)
    List<AppliedPolicy> findByTenure(@Param("nextTenureDate") Date tenure);

    @Modifying
    @Query(value="UPDATE appliedPolicy a SET a.nextPaymentDate = nextPaymentDate WHERE a.appliedPolicyId = appliedPolicyId",nativeQuery = true)
    void updateNextPaymentDate(@Param("appliedPolicyId") Long appliedPolicyId, @Param("nextPaymentDate") Date nextPaymentDate);

}


