package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.policyInfoService.PolicyInfoCommandService;
import com.parentsgowork.server.service.policyInfoService.PolicyInfoQueryService;
import com.parentsgowork.server.web.controller.specification.PolicyInfoSpecification;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PolicyInfo", description = "정책정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/policyInfo")
public class PolicyInfoController implements PolicyInfoSpecification {

    private final PolicyInfoCommandService policyInfoCommandService;
    private final PolicyInfoQueryService policyInfoQueryService;

    @GetMapping("")
    public ApiResponse<List<PolicyInfoResponseDTO.PolicyInfoListDTO>> getPolicyInfoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<PolicyInfoResponseDTO.PolicyInfoListDTO> response = policyInfoQueryService.getPolicyInfoList(userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{policyInfoId}")
    public ApiResponse<PolicyInfoResponseDTO.PolicyInfoDetailDTO> getPolicyInfoDetails(@PathVariable Long policyInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        PolicyInfoResponseDTO.PolicyInfoDetailDTO response = policyInfoQueryService.getPolicyInfoDetails(userId, policyInfoId);
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{policyInfoId}")
    public ApiResponse<PolicyInfoResponseDTO.DeletePolicyInfoDTO> deletePolicyInfo(@PathVariable("policyInfoId") Long policyInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        PolicyInfoResponseDTO.DeletePolicyInfoDTO response = policyInfoCommandService.delete(policyInfoId, userId);
        return ApiResponse.onSuccess(response);
    }
}
