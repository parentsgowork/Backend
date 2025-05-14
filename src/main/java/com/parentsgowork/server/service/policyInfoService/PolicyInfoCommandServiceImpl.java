package com.parentsgowork.server.service.policyInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.PolicyInfoHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.PolicyInfoConverter;
import com.parentsgowork.server.domain.PolicyInfo;
import com.parentsgowork.server.repository.PolicyInfoRepository;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyInfoCommandServiceImpl implements PolicyInfoCommandService {

    private final PolicyInfoRepository policyInfoRepository;
    private final UserRepository userRepository;

    @Override
    public PolicyInfoResponseDTO.DeletePolicyInfoDTO delete(Long userId, Long policyInfoId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        PolicyInfo policyInfo = policyInfoRepository.findByIdAndUserId(policyInfoId, userId)
                .orElseThrow(() -> new PolicyInfoHandler(ErrorStatus.POLICY_INFO_NOT_FOUND));

        policyInfoRepository.deleteById(policyInfoId);

        return PolicyInfoConverter.DeletePolicyInfoDTO(policyInfo);
    }
}
