package com.parentsgowork.server.domain;

import com.parentsgowork.server.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column
    private Long jobId;

    @Column
    private String companyName;

    @Column
    private String jobTitle;

    @Column
    private String pay;

    @Column
    private String time;

    @Column
    private String location;

    @Column
    private String deadline;

    @Column
    private String registrationDate;

}
