package com.parentsgowork.server.service.educationInfoService;

import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;

import java.util.List;

public interface EducationInfoQueryService {
    List<EducationInfoResponseDTO.EducationInfoListDTO> getEducationInfoList(Long userId);
    EducationInfoResponseDTO.EducationInfoDetailDTO getEducationInfoDetails(Long userId, Long educationInfoId);
}
