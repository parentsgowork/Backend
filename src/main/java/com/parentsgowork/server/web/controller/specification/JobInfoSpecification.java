package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoRequestDTO;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface JobInfoSpecification {

    @GetMapping("/search")
    @Operation(summary = "구직정보 저장", description = "저장하고 싶은 구직정보의 title과 content값을 보내주세요.")
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

}
