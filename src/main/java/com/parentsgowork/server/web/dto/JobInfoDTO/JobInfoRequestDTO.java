package com.parentsgowork.server.web.dto.JobInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class JobInfoRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveJobInfoDTO {
        private String title;
        private String content;
    }
}
