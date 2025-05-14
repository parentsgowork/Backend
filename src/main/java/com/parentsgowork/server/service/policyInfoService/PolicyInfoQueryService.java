package com.parentsgowork.server.service.policyInfoService;

import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;

import java.util.List;

public interface PolicyInfoQueryService {
    List<PolicyInfoResponseDTO.PolicyInfoListDTO> getPolicyInfoList(Long userId);
    PolicyInfoResponseDTO.PolicyInfoDetailDTO getPolicyInfoDetails(Long userId, Long policyInfoId);
}
