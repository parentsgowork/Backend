package com.parentsgowork.server.domain;

import com.parentsgowork.server.domain.common.BaseEntity;
import com.parentsgowork.server.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true, length = 50)
    private String primaryEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private Integer career;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinalEdu finalEdu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "refresh_token_id")
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBookmark> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PolicyInfo> policyInfos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EducationInfo> educationInfos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Resume> resumes;

    public void encodePassword(String password) {
        this.password = password;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }
}
