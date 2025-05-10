package com.parentsgowork.server.service.crawlingService;

import com.parentsgowork.server.crawling.JobPageCrawling;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final JobPageCrawling jobPageCrawler;

    @Override
    public List<JobCrawlingDTO.JobInfoDTO> getSeniorJobs(int page) {
        return jobPageCrawler.crawlJobs(page);
    }
}
