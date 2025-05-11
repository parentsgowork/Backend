package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.AuthenticationCode;
import com.parentsgowork.server.domain.enums.CodeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuthenticationCodeRepository extends JpaRepository<AuthenticationCode, Integer> {

    // 특정 이메일의 expirationDate가 지금 이후 & status가 유효한 데이터 찾기
    Optional<AuthenticationCode> findByEmailAndExpirationDateAfterAndStatus(String email, LocalDateTime currentDateTime, CodeStatus codeStatus);

    @Modifying
    @Transactional
    @Query("DELETE FROM AuthenticationCode ac WHERE ac.expirationDate < :threshold")
    int deleteByExpirationDateBefore(LocalDateTime threshold);
}
