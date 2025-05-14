package com.parentsgowork.server.web.dto.JobInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class JobInfoRequestDTO {

    // 구직정보 저장 시 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveJobInfoDTO {
        private String title;
        private String content;
    }
}
