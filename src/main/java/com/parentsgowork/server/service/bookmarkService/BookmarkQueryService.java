package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkQueryService {
    List<BookmarkResponseDTO.BookmarkListDTO> getBookmarkList(Long userId);

    BookmarkRequestDTO.BookmarkDetailDTO getBookmarkDetails(Long userId, Long bookmarkId);
}
