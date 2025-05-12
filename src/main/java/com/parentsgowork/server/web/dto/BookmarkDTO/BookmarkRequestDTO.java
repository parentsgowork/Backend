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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveBookmarkDTO {
        private Long id;
        private Long jobId;
        private String companyName;
        private String jobTitle;
        private String pay;
        private String time;
        private String location;
        private String deadline;
        private String registrationDate;
        private String detailUrl;
    }

}
