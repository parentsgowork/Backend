package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.domain.Bookmark;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkRequestDTO;
import com.parentsgowork.server.web.dto.BookmarkDTO.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookmarkSpecification {

    @GetMapping("")
    @Operation(summary = "내가 저장한 교육정보 리스트 조회", description = "내가 저장한 교육정보 리스트를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<List<BookmarkResponseDTO.EducationInfoListDTO>> getEducationInfoList();

    @GetMapping("{bookmarkId}")
    @Operation(summary = "특정 북마크 조회", description = "내가 저장한 북마크 중 특정 북마크를 조회합니다. bookmark 아이디 값을 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<BookmarkResponseDTO.BookmarkDetailInfoDTO> getBookmarkDetails(@PathVariable Long bookmarkId);

    @DeleteMapping("{bookmarkId}")
    @Operation(summary = "내가 저장한 북마크 삭제", description = "내가 저장한 북마크 리스트를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<BookmarkResponseDTO.DeleteBookmarkDTO> deleteBookmark(@PathVariable("bookmarkId") Long bookmarkId);

}
