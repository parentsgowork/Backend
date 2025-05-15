package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.JobInfo;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class JobInfoConverter {

    public static JobInfoResponseDTO.AddJobResultDTO toAddJobResultDTO(JobInfo jobInfo) {
        return JobInfoResponseDTO.AddJobResultDTO.builder()
                .title(jobInfo.getTitle())
                .content(jobInfo.getContent())
                .build();
    }

    public static List<JobInfoResponseDTO.JobInfoListDTO> getJobInfoListDTO(List<JobInfo> jobInfos) {
        return jobInfos.stream()
                .map(job -> JobInfoResponseDTO.JobInfoListDTO.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .content(job.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public static JobInfoResponseDTO.JobInfoDetailDTO getJobInfoDetailDTO(JobInfo jobInfo) {
        return JobInfoResponseDTO.JobInfoDetailDTO.builder()
                .title(jobInfo.getTitle())
                .content(jobInfo.getContent())
                .build();
    }


    public static JobInfoResponseDTO.DeleteJobInfoDTO DeleteJobInfoDTO(JobInfo jobInfo) {
        return JobInfoResponseDTO.DeleteJobInfoDTO.builder()
                .id(jobInfo.getId())
                .message("저장한 구직정보를 삭제하였습니다.")
                .build();
    }
}
