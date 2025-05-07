package com.parentsgowork.server.domain.enums;

public enum Region {
    // 수도권
    SEOUL("수도권"),
    GYEONGGI("수도권"),
    INCHEON("수도권"),
    // 강원권
    GANGWON("강원권"),
    // 충청권
    DAEJEON("충청권"),
    SEJONG("충청권"),
    CHUNGBUK("충청권");

    private final String areaGroup;

    Region(String areaGroup) {
        this.areaGroup = areaGroup;
    }

}
