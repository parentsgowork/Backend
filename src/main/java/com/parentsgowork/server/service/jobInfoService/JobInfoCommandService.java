package com.parentsgowork.server.service.jobInfoService;

import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoRequestDTO;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;

import java.util.List;

public interface JobInfoCommandService {

    List<JobInfoResponseDTO.AddJobResultDTO> addJobInfo(Long userId, List<JobInfoRequestDTO.SaveJobInfoDTO> saveJobInfoDTOList);

    JobInfoResponseDTO.DeleteJobInfoDTO delete(Long userId, Long jobInfoId);

}
