package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.BookmarkHandler;
import com.parentsgowork.server.converter.BookmarkConverter;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.repository.BookmarkRepository;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkQueryServiceImpl implements BookmarkQueryService {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public BookmarkResponseDTO.BookmarkDetailInfoDTO getBookmarkDetails(Long userId, Long bookmarkId) {

        Bookmark bookmark = bookmarkRepository.findByIdAndUserId(bookmarkId, userId)
                .orElseThrow(() -> new BookmarkHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND));

        return BookmarkConverter.toDetailDTO(bookmark);
    }

    @Override
    public List<BookmarkResponseDTO.EducationInfoListDTO> getEducationInfoList(Long userId) {

        List<EducationInfo> educationInfos = bookmarkRepository.findEducationInfoList(userId);

        if(educationInfos == null || educationInfos.isEmpty()) {
            throw new BookmarkHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND);
        }

        return BookmarkConverter.getEducationInfoListDTO(educationInfos);
    }
}
