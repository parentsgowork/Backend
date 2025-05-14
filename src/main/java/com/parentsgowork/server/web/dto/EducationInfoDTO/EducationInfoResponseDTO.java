package com.parentsgowork.server.web.dto.EducationInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EducationInfoResponseDTO {

    // 저장한 교육정보 리스트 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EducationInfoListDTO {
        private Long id;
        private String title;
        private String url;
    }

    // 저장한 교육정보 상세페이지 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EducationInfoDetailDTO {
        private Long id;
        private String title;
        private String url;
    }

    // 저장한 교육정보 삭제 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteEducationInfoDTO {
        private Long id;
        private String message;
    }
}
