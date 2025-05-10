package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.crawlingService.CrawlingService;
import com.parentsgowork.server.web.controller.specification.CrawlerSpecification;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Crawler", description = "크롤링 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/crawler")
public class CrawlerController implements CrawlerSpecification {

    private final CrawlingService crawlingService;

    //@Override
    @GetMapping("/senior-jobs")
    public ApiResponse<List<JobCrawlingDTO.JobInfoDTO>> jobCrawler(@RequestParam(defaultValue = "1") int page) {
        return ApiResponse.onSuccess(crawlingService.getSeniorJobs(page));
    }
}
