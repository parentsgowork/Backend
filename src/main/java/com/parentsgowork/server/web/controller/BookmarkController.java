package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.service.bookmarkService.BookmarkService;
import com.parentsgowork.server.web.controller.specification.BookmarkSpecification;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Bookmark", description = "북마크 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController implements BookmarkSpecification {

    private final BookmarkService bookmarkService;

    @PostMapping("/")
    public ApiResponse<Bookmark> bookmarkJob(@RequestBody BookmarkRequestDTO.jobInfoBookmarkDTO request) {
        return ApiResponse.onSuccess(bookmarkService.bookmarkJob(request.getJobId(), request.getPage()));
    }
}
