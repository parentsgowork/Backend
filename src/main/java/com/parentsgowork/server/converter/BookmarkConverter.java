package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkConverter {

    public static BookmarkRequestDTO.SaveBookmarkDTO toSaveBookmarkDTO(Bookmark bookmark) {
        return BookmarkRequestDTO.SaveBookmarkDTO.builder()
                .id(bookmark.getId())
                .jobId(bookmark.getJobId())
                .companyName(bookmark.getCompanyName())
                .jobTitle(bookmark.getJobTitle())
                .pay(bookmark.getPay())
                .time(bookmark.getTime())
                .location(bookmark.getLocation())
                .deadline(bookmark.getDeadline())
                .registrationDate(bookmark.getRegistrationDate())
                .detailUrl(bookmark.getDetailUrl())
                .build();
    }

    public static BookmarkResponseDTO.BookmarkDetailInfoDTO toDetailDTO(Bookmark bookmark) {
        return BookmarkResponseDTO.BookmarkDetailInfoDTO.builder()
                .id(bookmark.getId())
                .jobTitle(bookmark.getJobTitle())
                .companyName(bookmark.getCompanyName())
                .pay(bookmark.getPay())
                .time(bookmark.getTime())
                .location(bookmark.getLocation())
                .deadline(bookmark.getDeadline())
                .registrationDate(bookmark.getRegistrationDate())
                .detailUrl(bookmark.getDetailUrl())
                .build();
    }


    public static List<BookmarkResponseDTO.BookmarkListDTO> getBookmarkListDTO(List<Bookmark> bookmarks) {
        return bookmarks.stream()
                .map(bookmark -> BookmarkResponseDTO.BookmarkListDTO.builder()
                        .id(bookmark.getId())
                        .jobId(bookmark.getJobId())
                        .companyName(bookmark.getCompanyName())
                        .jobTitle(bookmark.getJobTitle())
                        .pay(bookmark.getPay())
                        .time(bookmark.getTime())
                        .location(bookmark.getLocation())
                        .deadline(bookmark.getDeadline())
                        .registrationDate(bookmark.getRegistrationDate())
                        .detailUrl(bookmark.getDetailUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static BookmarkResponseDTO.DeleteBookmarkDTO toDeletedBookmark(Bookmark bookmark) {
        return BookmarkResponseDTO.DeleteBookmarkDTO.builder()
                .id(bookmark.getId())
                .message("북마크를 삭제했습니다.")
                .build();
    }
}
