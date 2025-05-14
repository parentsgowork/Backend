package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.PolicyInfo;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PolicyInfoConverter {
    public static List<PolicyInfoResponseDTO.PolicyInfoListDTO> getPolicyInfoListDTO(List<PolicyInfo> policyInfos) {
        return policyInfos.stream()
                .map(policy -> PolicyInfoResponseDTO.PolicyInfoListDTO.builder()
                        .id(policy.getId())
                        .title(policy.getTitle())
                        .url(policy.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static PolicyInfoResponseDTO.PolicyInfoDetailDTO getPolicyInfoDetailDTO(PolicyInfo policyInfo) {
        return PolicyInfoResponseDTO.PolicyInfoDetailDTO.builder()
                .id(policyInfo.getId())
                .title(policyInfo.getTitle())
                .url(policyInfo.getUrl())
                .build();
    }


    public static PolicyInfoResponseDTO.DeletePolicyInfoDTO DeletePolicyInfoDTO(PolicyInfo policyInfo) {
        return PolicyInfoResponseDTO.DeletePolicyInfoDTO.builder()
                .id(policyInfo.getId())
                .message("저장한 정책정보를 삭제하였습니다.")
                .build();
    }
}
