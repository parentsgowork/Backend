package com.parentsgowork.server.service;

import com.parentsgowork.server.crawler.JobPageCrawler;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingService {

    private final JobPageCrawler jobPageCrawler;

    public List<JobCrawlingDTO.JobInfoDTO> getSeniorJobs(int page) {
        return jobPageCrawler.crawlJobs(page);
    }
}
