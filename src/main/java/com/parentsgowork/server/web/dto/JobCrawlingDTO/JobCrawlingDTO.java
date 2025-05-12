package com.parentsgowork.server.web.dto.JobCrawlingDTO;


import lombok.*;

public class JobCrawlingDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobInfoDTO {
        private Long id;
        private String companyName;
        private String jobTitle;
        private String pay;
        private String workTime;
        private String location;
        private String deadline;
        private String registrationDate;
        private String detailUrl;
    }

}
