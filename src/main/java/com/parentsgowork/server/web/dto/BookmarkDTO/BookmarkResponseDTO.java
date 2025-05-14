package com.parentsgowork.server.web.dto.BookmarkDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookmarkResponseDTO {


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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteBookmarkDTO {
        private Long id;
        private String message; // ex) 북마크 삭제가 완료되었습니다.
    }

}
