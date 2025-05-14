package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.domain.EducationInfo;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkConverter {

    public static List<BookmarkResponseDTO.EducationInfoListDTO> getEducationInfoListDTO(List<EducationInfo> educationInfos) {
        return educationInfos.stream()
                .map(education -> BookmarkResponseDTO.EducationInfoListDTO.builder()
                        .id(education.getId())
                        .title(education.getTitle())
                        .url(education.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static BookmarkResponseDTO.EducationInfoDetailDTO getEducationInfoDetailDTO(EducationInfo educationInfo) {
        return BookmarkResponseDTO.EducationInfoDetailDTO.builder()
                .id(educationInfo.getId())
                .title(educationInfo.getTitle())
                .url(educationInfo.getUrl())
                .build();
    }


    public static BookmarkResponseDTO.DeleteBookmarkDTO toDeletedBookmark(Bookmark bookmark) {
        return BookmarkResponseDTO.DeleteBookmarkDTO.builder()
                .id(bookmark.getId())
                .message("북마크를 삭제했습니다.")
                .build();
    }
}
