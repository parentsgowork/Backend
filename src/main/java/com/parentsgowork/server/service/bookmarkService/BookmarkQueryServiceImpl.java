package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.BookmarkHandler;
import com.parentsgowork.server.converter.BookmarkConverter;
import com.parentsgowork.server.domain.Bookmark;
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
    public List<BookmarkResponseDTO.BookmarkListDTO> getBookmarkList(Long userId) {

        List<Bookmark> bookmarks = bookmarkRepository.findBookmarkList(userId);

        return BookmarkConverter.getBookmarkListDTO(bookmarks);
    }
}
