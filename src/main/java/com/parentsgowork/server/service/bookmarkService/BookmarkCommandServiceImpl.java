package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.BookmarkHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.BookmarkConverter;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.BookmarkRepository;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookmarkCommandServiceImpl implements BookmarkCommandService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @Override
    public BookmarkResponseDTO.DeleteBookmarkDTO delete(Long userId, Long bookmarkId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Bookmark bookmark = bookmarkRepository.findByIdAndUserId(bookmarkId, userId)
                        .orElseThrow(() -> new BookmarkHandler(ErrorStatus.EDUCATION_INFO_NOT_FOUND));

        bookmarkRepository.deleteById(bookmarkId);

        return BookmarkConverter.toDeletedBookmark(bookmark);
    }
}
