package com.parentsgowork.server.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "JobInfo", description = "구직정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobInfo")
public class JobInfoController {

    @Value("${seoul.openapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public ResponseEntity<List<JobInfoResponseDTO.SaveJobResultDTO>> getParsedJobInfo() {
        int start = 1;
        int end = 3;
        String url = String.format(
                "http://openapi.seoul.go.kr:8088/%s/json/GetJobInfo/%d/%d/",
                apiKey, start, end
        );

        String rawJson = restTemplate.getForObject(url, String.class);

        try {
            JobInfoResponseDTO response = objectMapper.readValue(rawJson, JobInfoResponseDTO.class);

            List<JobInfoResponseDTO.SaveJobResultDTO> resultList =
                    response.getJobInfo().getRow().stream()
                            .map(row -> JobInfoResponseDTO.SaveJobResultDTO.builder()
                                    .title(row.getTitle())
                                    .content(row.getContent())
                                    .build())
                            .collect(Collectors.toList());


            return ResponseEntity.ok(resultList);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


}
