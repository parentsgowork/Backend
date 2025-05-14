package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class EducationInfoConverter {

    public static List<EducationInfoResponseDTO.EducationInfoListDTO> getEducationInfoListDTO(List<EducationInfo> educationInfos) {
        return educationInfos.stream()
                .map(education -> EducationInfoResponseDTO.EducationInfoListDTO.builder()
                        .id(education.getId())
                        .title(education.getTitle())
                        .url(education.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static EducationInfoResponseDTO.EducationInfoDetailDTO getEducationInfoDetailDTO(EducationInfo educationInfo) {
        return EducationInfoResponseDTO.EducationInfoDetailDTO.builder()
                .id(educationInfo.getId())
                .title(educationInfo.getTitle())
                .url(educationInfo.getUrl())
                .build();
    }


    public static EducationInfoResponseDTO.DeleteEducationInfoDTO DeleteEducationInfoDTO(EducationInfo educationInfo) {
        return EducationInfoResponseDTO.DeleteEducationInfoDTO.builder()
                .id(educationInfo.getId())
                .message("저장한 교육정보를 삭제하였습니다.")
                .build();
    }
}
