package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;

public interface BookmarkCommandService {
    BookmarkRequestDTO.BookmarkDetailDTO bookmarkJob(Long userId, Long jobId, int page);
}
