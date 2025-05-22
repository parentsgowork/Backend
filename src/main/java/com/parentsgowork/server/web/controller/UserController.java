package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.service.userService.UserCommandService;
import com.parentsgowork.server.service.userService.UserQueryService;
import com.parentsgowork.server.web.controller.specification.UserSpecification;
import com.parentsgowork.server.web.dto.PolicyInfoDTO.PolicyInfoResponseDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserSpecification {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @GetMapping("")
    public ApiResponse<UserResponseDTO.UserPageInfoDTO> getUserPageInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        UserResponseDTO.UserPageInfoDTO response = userQueryService.getUserPageInfo(userId);
        return ApiResponse.onSuccess(response);
    }

    @Override
    @PatchMapping("/password")
    public ApiResponse<Void> findPassword(@RequestBody @Valid UserRequestDTO.PasswordDTO passwordDTO) {
        userCommandService.updatePassword(passwordDTO);
        return ApiResponse.onSuccess();
    }

    @Override
    @PatchMapping("")
    public ApiResponse<UserResponseDTO.DeleteUserResponseDTO> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        UserResponseDTO.DeleteUserResponseDTO deleteUser = userCommandService.delete(userId);
        return ApiResponse.onSuccess(deleteUser);
    }
}
