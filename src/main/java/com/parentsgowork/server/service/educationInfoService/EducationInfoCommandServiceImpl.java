package com.parentsgowork.server.service.educationInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.EducationInfoHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.EducationInfoConverter;
import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.repository.EducationInfoRepository;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInfoCommandServiceImpl implements EducationInfoCommandService {

    private final EducationInfoRepository educationInfoRepository;
    private final UserRepository userRepository;

    @Override
    public EducationInfoResponseDTO.DeleteEducationInfoDTO delete(Long educationInfoId, Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        EducationInfo educationInfo = educationInfoRepository.findByIdAndUserId(educationInfoId, userId)
                .orElseThrow(() -> new EducationInfoHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND));

        educationInfoRepository.deleteById(educationInfoId);

        return EducationInfoConverter.DeleteEducationInfoDTO(educationInfo);
    }
}
