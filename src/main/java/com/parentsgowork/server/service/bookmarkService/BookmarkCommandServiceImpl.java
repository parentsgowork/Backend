package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.BookmarkHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.BookmarkConverter;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.BookmarkRepository;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.service.crawlingService.CrawlingService;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO.JobInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkCommandServiceImpl implements BookmarkCommandService {

    private final BookmarkRepository bookmarkRepository;
    private final CrawlingService crawlingService;
    private final UserRepository userRepository;

    @Override
    public BookmarkRequestDTO.BookmarkDetailDTO bookmarkJob(Long userId, Long jobId, int page) {

        // 해당 페이지의 크롤링 결과 불러오기
        List<JobInfoDTO> crawledJobs = crawlingService.getSeniorJobs(page);

        // 해당 ID의 채용 정보 찾기
        JobInfoDTO job = crawledJobs.stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new BookmarkHandler(ErrorStatus.CRAWLING_NO_RESULTS));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .jobId(jobId)
                .companyName(job.getCompanyName())
                .jobTitle(job.getJobTitle())
                .pay(job.getPay())
                .time(job.getWorkTime())
                .location(job.getLocation())
                .deadline(job.getDeadline())
                .registrationDate(job.getRegistrationDate())
                .build();

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return BookmarkConverter.toDetailDTO(savedBookmark);
    }

    @Override
    public BookmarkResponseDTO.DeleteBookmarkDTO delete(Long userId, Long bookmarkId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Bookmark bookmark = bookmarkRepository.findByIdAndUserId(bookmarkId, userId)
                        .orElseThrow(() -> new BookmarkHandler(ErrorStatus.BOOKMARK_NOT_FOUND));

        bookmarkRepository.deleteById(bookmarkId);

        return BookmarkConverter.toDeletedBookmark(bookmark);
    }
}
