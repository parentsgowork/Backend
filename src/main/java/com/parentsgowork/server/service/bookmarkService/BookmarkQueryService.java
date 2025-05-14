package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkQueryService {

    List<BookmarkResponseDTO.EducationInfoListDTO> getEducationInfoList(Long userId);
    BookmarkResponseDTO.EducationInfoDetailDTO getEducationInfoDetails(Long userId, Long educationInfoId);
}
