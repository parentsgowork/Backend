package com.parentsgowork.server.service.educationInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.EducationInfoHandler;
import com.parentsgowork.server.converter.EducationInfoConverter;
import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.repository.EducationInfoRepository;
import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoQueryServiceImpl implements EducationInfoQueryService {

    private final EducationInfoRepository educationInfoRepository;

    @Override
    public List<EducationInfoResponseDTO.EducationInfoListDTO> getEducationInfoList(Long userId) {

        List<EducationInfo> educationInfos = educationInfoRepository.findEducationInfoList(userId);

        if(educationInfos == null || educationInfos.isEmpty()) {
            throw new EducationInfoHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND);
        }

        return EducationInfoConverter.getEducationInfoListDTO(educationInfos);
    }

    @Override
    public EducationInfoResponseDTO.EducationInfoDetailDTO getEducationInfoDetails(Long userId, Long educationInfoId) {

        EducationInfo educationInfo = educationInfoRepository.findByIdAndUserId(educationInfoId, userId)
                .orElseThrow(() -> new EducationInfoHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND));

        return EducationInfoConverter.getEducationInfoDetailDTO(educationInfo);
    }
}