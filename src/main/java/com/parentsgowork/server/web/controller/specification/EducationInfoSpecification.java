package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.web.dto.EducationInfoDTO.EducationInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EducationInfoSpecification {

    @GetMapping("")
    @Operation(summary = "내가 저장한 교육정보 리스트 조회", description = "내가 저장한 교육정보 리스트를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4001", description = "❌ 교육정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<List<EducationInfoResponseDTO.EducationInfoListDTO>> getEducationInfoList();

    @GetMapping("/{educationInfoId}")
    @Operation(summary = "특정 교육정보 조회", description = "내가 저장한 특정 교육정보를 조회합니다. 교육정보 아이디 값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4001", description = "❌ 교육정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<EducationInfoResponseDTO.EducationInfoDetailDTO> getEducationInfoDetails(@PathVariable Long educationInfoId);

    @DeleteMapping("/{educationInfoId}")
    @Operation(summary = "내가 저장한 교육정보 삭제", description = "내가 저장한 특정 교육정보를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOOKMARK4001", description = "❌ 교육정보가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<EducationInfoResponseDTO.DeleteEducationInfoDTO> deleteEducationInfo(@PathVariable("educationInfoId") Long educationInfoId);

}
