package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobInfoRepository extends JpaRepository<JobInfo, Long> {
    @Query("SELECT ji FROM JobInfo ji " +
            "WHERE ji. user.id = :userId")
    List<JobInfo> findJobInfoList(@Param("userId") Long userId);

    Optional<JobInfo> findByIdAndUserId(@Param("jobInfoId") Long jobInfoId, @Param("userId") Long userId);

    boolean existsByTitleAndContent(String title, String content);

}
