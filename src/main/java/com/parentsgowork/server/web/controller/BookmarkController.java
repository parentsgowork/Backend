package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.bookmarkService.BookmarkCommandService;
import com.parentsgowork.server.service.bookmarkService.BookmarkQueryService;
import com.parentsgowork.server.web.controller.specification.BookmarkSpecification;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
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

    @PostMapping("")
    public ApiResponse<BookmarkRequestDTO.SaveBookmarkDTO> bookmarkJob(@RequestBody BookmarkRequestDTO.jobInfoBookmarkDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        BookmarkRequestDTO.SaveBookmarkDTO response = bookmarkCommandService.bookmarkJob(userId, request.getJobId(), request.getPage());
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("")
    public ApiResponse<List<BookmarkResponseDTO.BookmarkListDTO>> getBookmarkList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<BookmarkResponseDTO.BookmarkListDTO> response = bookmarkQueryService.getBookmarkList(userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("{bookmarkId}")
    public ApiResponse<BookmarkResponseDTO.BookmarkDetailInfoDTO> getBookmarkDetails(@PathVariable Long bookmarkId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        BookmarkResponseDTO.BookmarkDetailInfoDTO response = bookmarkQueryService.getBookmarkDetails(userId, bookmarkId);
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("{bookmarkId}")
    public ApiResponse<BookmarkResponseDTO.DeleteBookmarkDTO> deleteBookmark(@PathVariable("bookmarkId") Long bookmarkId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        BookmarkResponseDTO.DeleteBookmarkDTO deleteBookmark = bookmarkCommandService.delete(bookmarkId, userId);
        return ApiResponse.onSuccess(deleteBookmark);
    }
}
