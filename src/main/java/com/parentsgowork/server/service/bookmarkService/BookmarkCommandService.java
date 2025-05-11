package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

public interface BookmarkCommandService {
    BookmarkRequestDTO.BookmarkDetailDTO bookmarkJob(Long userId, Long jobId, int page);

    BookmarkResponseDTO.DeleteBookmarkDTO delete(Long userId, Long bookmarkId);
}
