package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.BookmarkHandler;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.repository.BookmarkRepository;
import com.parentsgowork.server.service.crawlingService.CrawlingService;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO.JobInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final CrawlingService crawlingService; // 크롤링 서비스 의존성 주입

    @Override
    public Bookmark bookmarkJob(Long jobId, int page) {

        // 해당 페이지의 크롤링 결과 불러오기
        List<JobInfoDTO> crawledJobs = crawlingService.getSeniorJobs(page);

        // 해당 ID의 채용 정보 찾기
        JobInfoDTO job = crawledJobs.stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new BookmarkHandler(ErrorStatus.CRAWLING_NO_RESULTS));

        // Bookmark 엔티티로 변환 후 저장
        Bookmark bookmark = Bookmark.builder()
                .companyName(job.getCompanyName())
                .jobTitle(job.getJobTitle())
                .pay(job.getPay())
                .time(job.getWorkTime())
                .location(job.getLocation())
                .deadline(job.getDeadline())
                .registrationDate(job.getRegistrationDate())
                .build();

        return bookmarkRepository.save(bookmark);
    }
}
