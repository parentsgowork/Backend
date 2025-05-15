package com.parentsgowork.server.service.jobInfoService;

import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;

import java.util.List;

public interface JobInfoQueryService {

    List<JobInfoResponseDTO.JobInfoResultDTO> getJobInfoList(Long userId);
    JobInfoResponseDTO.JobInfoDetailDTO getJobInfoDetails(Long userId, Long jobInfoId);
}
