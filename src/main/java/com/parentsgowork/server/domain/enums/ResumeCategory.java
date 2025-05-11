package com.parentsgowork.server.domain.enums;

public enum ResumeCategory {
    GROWTH("성장과정"),
    MOTIVATION("지원동기"),
    ASPIRATION("입사포부"),
    STRENGTH("강점약점"),
    PROJECT_EXPERIENCE("프로젝트경험");

    private final String displayName;

    ResumeCategory(String displayName) {
        this.displayName = displayName;
    }

}
