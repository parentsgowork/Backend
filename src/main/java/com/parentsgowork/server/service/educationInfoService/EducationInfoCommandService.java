package com.parentsgowork.server.service.educationInfoService;

import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;

public interface EducationInfoCommandService {
    EducationInfoResponseDTO.DeleteEducationInfoDTO delete(Long userId, Long educationInfoId);

}
