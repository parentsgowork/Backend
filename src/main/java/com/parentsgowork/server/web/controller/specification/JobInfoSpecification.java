package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoRequestDTO;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface JobInfoSpecification {

    @GetMapping("/search")
    @Operation(summary = "구직정보 조회", description = "구직정보를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ResponseEntity<List<JobInfoResponseDTO.JobInfoResultDTO>> getParsedJobInfo();

    @GetMapping("/add")
    @Operation(summary = "구직정보 저장", description = "저장하고 싶은 구직정보의 title과 content값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<List<JobInfoResponseDTO.AddJobResultDTO>> addJobInfo(@RequestBody List<JobInfoRequestDTO.SaveJobInfoDTO> saveJobInfoDTOList);

    @GetMapping("")
    @Operation(summary = "내가 저장한 구직정보 리스트 조회", description = "내가 저장한 구직정보 리스트를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<List<JobInfoResponseDTO.JobInfoListDTO>> getJobInfoList();

    @GetMapping("/{jobInfoId}")
    @Operation(summary = "특정 구직정보 조회", description = "내가 저장한 특정 구직정보를 조회합니다. 구직정보 아이디 값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<JobInfoResponseDTO.JobInfoDetailDTO> getJobInfoDetails(@PathVariable Long jobInfoId);

    @DeleteMapping("/{jobInfoId}")
    @Operation(summary = "내가 저장한 구직정보 삭제", description = "내가 저장한 특정 구직정보를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<JobInfoResponseDTO.DeleteJobInfoDTO> deleteJobInfo(@PathVariable("jobInfoId") Long jobInfoId);

}
