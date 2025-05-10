package com.parentsgowork.server.service.crawlingService;

import com.parentsgowork.server.crawling.JobInfoCrawling;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final JobInfoCrawling jobPageCrawler;

    @Override
    public List<JobCrawlingDTO.JobInfoDTO> getSeniorJobs(int page) {
        return jobPageCrawler.crawlJobs(page);
    }
}
