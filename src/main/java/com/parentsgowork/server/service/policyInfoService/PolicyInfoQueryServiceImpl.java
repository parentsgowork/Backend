package com.parentsgowork.server.service.policyInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.PolicyInfoHandler;
import com.parentsgowork.server.converter.PolicyInfoConverter;
import com.parentsgowork.server.domain.PolicyInfo;
import com.parentsgowork.server.repository.PolicyInfoRepository;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyInfoQueryServiceImpl implements PolicyInfoQueryService {

    private final PolicyInfoRepository policyInfoRepository;

    @Override
    public List<PolicyInfoResponseDTO.PolicyInfoListDTO> getPolicyInfoList(Long userId) {

        List<PolicyInfo> policyInfos = policyInfoRepository.findPolicyInfoList(userId);

        if(policyInfos == null || policyInfos.isEmpty()) {
            throw new PolicyInfoHandler(ErrorStatus.POLICY_INFO_NOT_FOUND);
        }

        return PolicyInfoConverter.getPolicyInfoListDTO(policyInfos);
    }

    @Override
    public PolicyInfoResponseDTO.PolicyInfoDetailDTO getPolicyInfoDetails(Long userId, Long policyInfoId) {

        PolicyInfo policyInfo = policyInfoRepository.findByIdAndUserId(policyInfoId, userId)
                .orElseThrow(() -> new PolicyInfoHandler(ErrorStatus.POLICY_INFO_NOT_FOUND));

        return PolicyInfoConverter.getPolicyInfoDetailDTO(policyInfo);
    }
}
