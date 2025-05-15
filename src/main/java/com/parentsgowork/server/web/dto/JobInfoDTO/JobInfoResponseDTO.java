package com.parentsgowork.server.web.dto.JobInfoDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobInfoResponseDTO {

    @JsonProperty("GetJobInfo")
    private JobInfoWrapper jobInfo;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JobInfoWrapper {
        private List<Row> row;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {
        @JsonProperty("JO_SJ")
        private String title;

        @JsonProperty("DTY_CN")
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobInfoResultDTO {
        private String title;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddJobResultDTO {
        private String title;
        private String content;
    }

    // 저장한 구직정보 상세페이지 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JobInfoDetailDTO {
        private String title;
        private String content;
    }

    // 저장한 구직정보 삭제 dto
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteJobInfoDTO {
        private Long id;
        private String message;
    }
}
