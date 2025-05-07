package com.parentsgowork.server.domain.enums;

public enum FinalEdu {

    HIGH_SCHOOL("고등학교 졸업"),
    ASSOCIATE("전문대학 졸업"),
    BACHELOR("대학교 졸업"),
    MASTER("석사 학위"),
    DOCTOR("박사 학위");

    private final String description;

    FinalEdu(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
