package com.parentsgowork.server.web.dto.PolicyInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PolicyInfoResponseDTO {

    // 저장한 정책정보 리스트 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PolicyInfoListDTO {
        private Long id;
        private String title;
        private String url;
    }

    // 저장한 정책정보 상세페이지 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PolicyInfoDetailDTO {
        private Long id;
        private String title;
        private String url;
    }

    // 저장한 정책정보 삭제 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeletePolicyInfoDTO {
        private Long id;
        private String message;
    }
}
