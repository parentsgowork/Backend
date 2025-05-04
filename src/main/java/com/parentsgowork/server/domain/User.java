package com.parentsgowork.server.domain;

import com.parentsgowork.server.domain.common.BaseEntity;
import com.parentsgowork.server.domain.enums.Gender;
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

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String region;

    // refreshToken 부분은 추후에 수정
    @Column(columnDefinition = "Text")
    private String refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBookmark> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PolicyInfo> policyInfos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EducationInfo> educationInfos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Resume> resumes;
}
