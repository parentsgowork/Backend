package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.bookmarkService.BookmarkCommandService;
import com.parentsgowork.server.service.bookmarkService.BookmarkQueryService;
import com.parentsgowork.server.web.controller.specification.BookmarkSpecification;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bookmark", description = "북마크 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController implements BookmarkSpecification {

    private final BookmarkCommandService bookmarkCommandService;
    private final BookmarkQueryService bookmarkQueryService;

    @GetMapping("/educationInfo")
    public ApiResponse<List<BookmarkResponseDTO.EducationInfoListDTO>> getEducationInfoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<BookmarkResponseDTO.EducationInfoListDTO> response = bookmarkQueryService.getEducationInfoList(userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/educationInfo/{educationInfoId}")
    public ApiResponse<BookmarkResponseDTO.EducationInfoDetailDTO> getEducationInfoDetails(@PathVariable Long educationInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        BookmarkResponseDTO.EducationInfoDetailDTO response = bookmarkQueryService.getEducationInfoDetails(userId, educationInfoId);
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/educationInfo/{educationInfoId}")
    public ApiResponse<BookmarkResponseDTO.DeleteBookmarkDTO> deleteBookmark(@PathVariable("educationInfoId") Long educationInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        BookmarkResponseDTO.DeleteBookmarkDTO deleteBookmark = bookmarkCommandService.delete(educationInfoId, userId);
        return ApiResponse.onSuccess(deleteBookmark);
    }
}
