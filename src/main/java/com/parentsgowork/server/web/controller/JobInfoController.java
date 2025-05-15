package com.parentsgowork.server.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.jobInfoService.JobInfoCommandService;
import com.parentsgowork.server.service.jobInfoService.JobInfoQueryService;
import com.parentsgowork.server.web.controller.specification.JobInfoSpecification;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoRequestDTO;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "JobInfo", description = "구직정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobInfo")
public class JobInfoController implements JobInfoSpecification {

    @Value("${seoul.openapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JobInfoCommandService jobInfoCommandService;
    private final JobInfoQueryService jobInfoQueryService;

    @GetMapping("/search")
    public ResponseEntity<List<JobInfoResponseDTO.JobInfoResultDTO>> getParsedJobInfo() {
        int start = 1;
        int end = 3;
        String url = String.format(
                "http://openapi.seoul.go.kr:8088/%s/json/GetJobInfo/%d/%d/",
                apiKey, start, end
        );

        String rawJson = restTemplate.getForObject(url, String.class);

        try {
            JobInfoResponseDTO response = objectMapper.readValue(rawJson, JobInfoResponseDTO.class);

            List<JobInfoResponseDTO.JobInfoResultDTO> resultList =
                    response.getJobInfo().getRow().stream()
                            .map(row -> JobInfoResponseDTO.JobInfoResultDTO.builder()
                                    .title(row.getTitle())
                                    .content(row.getContent())
                                    .build())
                            .collect(Collectors.toList());


            return ResponseEntity.ok(resultList);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/add")
    public ApiResponse<List<JobInfoResponseDTO.AddJobResultDTO>> addJobInfo(@RequestBody List<JobInfoRequestDTO.SaveJobInfoDTO> saveJobInfoDTOList) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<JobInfoResponseDTO.AddJobResultDTO> response = jobInfoCommandService.addJobInfo(userId, saveJobInfoDTOList);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("")
    public ApiResponse<List<JobInfoResponseDTO.JobInfoListDTO>> getJobInfoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<JobInfoResponseDTO.JobInfoListDTO> response = jobInfoQueryService.getJobInfoList(userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{jobInfoId}")
    public ApiResponse<JobInfoResponseDTO.JobInfoDetailDTO> getJobInfoDetails(@PathVariable Long jobInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        JobInfoResponseDTO.JobInfoDetailDTO response = jobInfoQueryService.getJobInfoDetails(userId, jobInfoId);
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{jobInfoId}")
    public ApiResponse<JobInfoResponseDTO.DeleteJobInfoDTO> deleteJobInfo(@PathVariable("jobInfoId") Long jobInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        JobInfoResponseDTO.DeleteJobInfoDTO response = jobInfoCommandService.delete(jobInfoId, userId);
        return ApiResponse.onSuccess(response);
    }

}
