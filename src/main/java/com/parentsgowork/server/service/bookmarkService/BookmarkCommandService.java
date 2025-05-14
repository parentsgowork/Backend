package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

public interface BookmarkCommandService {
    BookmarkResponseDTO.DeleteBookmarkDTO delete(Long userId, Long bookmarkId);
}
