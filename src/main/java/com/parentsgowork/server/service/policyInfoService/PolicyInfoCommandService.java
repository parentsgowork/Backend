package com.parentsgowork.server.service.policyInfoService;


import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;

public interface PolicyInfoCommandService {
    PolicyInfoResponseDTO.DeletePolicyInfoDTO delete(Long policyInfoId, Long userId);

}
