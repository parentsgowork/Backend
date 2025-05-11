package com.parentsgowork.server.service.crawlingService;

import com.parentsgowork.server.crawling.JobInfoCrawling;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final JobInfoCrawling jobPageCrawling;

    @Override
    public List<JobCrawlingDTO.JobInfoDTO> getSeniorJobs(int page) {
        List<JobCrawlingDTO.JobInfoDTO> jobs = jobPageCrawling.crawlJobs(page);

        long startId = (long) (page - 1) * jobs.size() + 1;

        for (int i = 0; i < jobs.size(); i++) {
            jobs.get(i).setId(startId + i);
        }

        return jobs;
    }

}
