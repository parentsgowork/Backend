package com.parentsgowork.server.web.dto.BookmarkDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookmarkRequestDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class jobInfoBookmarkDTO {
        private Long jobId;
        private Integer page;
    }
}
