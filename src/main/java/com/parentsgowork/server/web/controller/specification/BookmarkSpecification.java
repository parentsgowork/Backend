package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookmarkSpecification {

    @PostMapping("/")
    @Operation(summary = "저장하고 싶은 채용 정보를 북마크합니다. <br> job 아이디와 페이지 값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<Bookmark> bookmarkJob(@RequestBody BookmarkRequestDTO.jobInfoBookmarkDTO request);
}
