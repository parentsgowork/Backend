package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.educationInfoService.EducationInfoCommandService;
import com.parentsgowork.server.service.educationInfoService.EducationInfoQueryService;
import com.parentsgowork.server.web.controller.specification.EducationInfoSpecification;
import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EducationInfo", description = "교육정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/educationInfo")
public class EducationInfoController implements EducationInfoSpecification {

    private final EducationInfoCommandService educationInfoCommandService;
    private final EducationInfoQueryService educationInfoQueryService;

    @GetMapping("")
    public ApiResponse<List<EducationInfoResponseDTO.EducationInfoListDTO>> getEducationInfoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<EducationInfoResponseDTO.EducationInfoListDTO> response = educationInfoQueryService.getEducationInfoList(userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{educationInfoId}")
    public ApiResponse<EducationInfoResponseDTO.EducationInfoDetailDTO> getEducationInfoDetails(@PathVariable Long educationInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        EducationInfoResponseDTO.EducationInfoDetailDTO response = educationInfoQueryService.getEducationInfoDetails(userId, educationInfoId);
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{educationInfoId}")
    public ApiResponse<EducationInfoResponseDTO.DeleteEducationInfoDTO> deleteEducationInfo(@PathVariable("educationInfoId") Long educationInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        EducationInfoResponseDTO.DeleteEducationInfoDTO response = educationInfoCommandService.delete(educationInfoId, userId);
        return ApiResponse.onSuccess(response);
    }
}
