package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkQueryService {
    BookmarkResponseDTO.BookmarkDetailInfoDTO getBookmarkDetails(Long userId, Long bookmarkId);

    List<BookmarkResponseDTO.EducationInfoListDTO> getEducationInfoList(Long userId);
}
