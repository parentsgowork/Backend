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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyInfoCommandServiceImpl implements PolicyInfoCommandService {

    private final PolicyInfoRepository policyInfoRepository;
    private final UserRepository userRepository;

    @Override
    public PolicyInfoResponseDTO.DeletePolicyInfoDTO delete(Long policyInfoId, Long userId) {
        log.info("[삭제 시도] userId: {}, policyInfoId: {}", userId, policyInfoId);

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        PolicyInfo policyInfo = policyInfoRepository.findByIdAndUserId(policyInfoId, userId)
                .orElseThrow(() -> {
                    log.warn("[삭제 실패] policyInfoId: {}는 userId: {}의 데이터가 아님 또는 없음", policyInfoId, userId);
                    return new PolicyInfoHandler(ErrorStatus.POLICY_INFO_NOT_FOUND);
                });

        policyInfoRepository.deleteById(policyInfoId);

        log.info("[삭제 성공] policyInfoId: {}", policyInfoId);

        return PolicyInfoConverter.DeletePolicyInfoDTO(policyInfo);
    }

}
