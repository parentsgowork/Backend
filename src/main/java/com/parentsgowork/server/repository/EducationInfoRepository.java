package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long>  {
    @Query("SELECT ei FROM EducationInfo ei " +
            "WHERE ei. user.id = :userId")
    List<EducationInfo> findEducationInfoList(@Param("userId") Long userId);

    Optional<EducationInfo> findByIdAndUserId(@Param("educationInfoId") Long educationInfoId, @Param("userId") Long userId);
}
