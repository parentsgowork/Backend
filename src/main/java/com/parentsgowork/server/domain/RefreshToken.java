package com.parentsgowork.server.domain;

import com.parentsgowork.server.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 512)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @OneToOne(mappedBy = "refreshToken")
    private User user;
}
