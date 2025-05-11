package com.parentsgowork.server.domain;

import com.parentsgowork.server.domain.common.BaseEntity;
import com.parentsgowork.server.domain.enums.CodeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AuthenticationCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일
    @Column(length = 50, nullable = false)
    private String email;

    // 인증 코드
    @Column(unique = true, nullable = false)
    private String code;

    // 인증 여부
    @Column(nullable = false)
    private Boolean isVerified;

    // 사용 가능 여부
    @Enumerated(EnumType.STRING)
    private CodeStatus status;

    // 인증 유효기간
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public void updateStatus(CodeStatus status) {
        this.status = status;
    }

    public void update() {
        this.isVerified = true;
        this.status = CodeStatus.EXPIRED;
    }
}
