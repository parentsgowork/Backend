package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PolicyInfoSpecification {

    @GetMapping("")
    @Operation(summary = "내가 저장한 고용 정책/복지 리스트 조회", description = "내가 저장한 고용 정책/복지 리스트를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4002", description = "❌ 고용 정책/복지 정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<List<PolicyInfoResponseDTO.PolicyInfoListDTO>> getPolicyInfoList();

    @GetMapping("/{policyInfoId}")
    @Operation(summary = "특정 고용 정책/복지 조회", description = "내가 저장한 특정 고용 정책/복지를 조회합니다. 고용 정책/복지 아이디 값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4002", description = "❌ 고용 정책/복지 정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<PolicyInfoResponseDTO.PolicyInfoDetailDTO> getPolicyInfoDetails(@PathVariable Long policyInfoId);

    @DeleteMapping("/{policyInfoId}")
    @Operation(summary = "내가 저장한 고용 정책/복지 삭제", description = "내가 저장한 특정 고용 정책/복지를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4002", description = "❌ 고용 정책/복지 정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<PolicyInfoResponseDTO.DeletePolicyInfoDTO> deletePolicyInfo(@PathVariable("policyInfoId") Long policyInfoId);

}
