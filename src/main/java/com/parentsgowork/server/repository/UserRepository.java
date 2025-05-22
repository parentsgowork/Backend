package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.domain.JobInfo;
import com.parentsgowork.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByPrimaryEmail(String primaryEmail);

    boolean existsByPrimaryEmail(String primaryEmail);

    @Query("SELECT user.password FROM User user WHERE user.primaryEmail = :primaryEmail")
    String findPasswordByPrimaryEmail(@Param("primaryEmail") String primaryEmail);

    // status가 inactive이고, updated_at이 30일 지난 사용자 삭제
    @Modifying
    @Query("DELETE FROM User u WHERE u.status = 'inactive' AND u.updatedAt <= :threshold")
    int deleteByStatusAndUpdatedAtBefore(@Param("threshold") LocalDateTime threshold);

    @Query("SELECT u.refreshToken.id FROM User u WHERE u.status = 'INACTIVE' AND u.updatedAt < :threshold")
    List<Long> findRefreshTokenIdsForInactiveUsers(@Param("threshold") LocalDateTime threshold);
}
