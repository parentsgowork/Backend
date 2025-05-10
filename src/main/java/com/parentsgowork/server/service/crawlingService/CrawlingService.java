package com.parentsgowork.server.service.crawlingService;

import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import java.util.List;

public interface CrawlingService {
    List<JobCrawlingDTO.JobInfoDTO> getSeniorJobs(int page);
}
