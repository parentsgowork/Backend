package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.PolicyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PolicyInfoRepository  extends JpaRepository<PolicyInfo, Long> {

    @Query("SELECT pi FROM PolicyInfo pi " +
            "WHERE pi. user.id = :userId")
    List<PolicyInfo> findPolicyInfoList(@Param("userId") Long userId);

    Optional<PolicyInfo> findByIdAndUserId(@Param("policyInfoId") Long policyInfoId, @Param("userId") Long userId);
}
