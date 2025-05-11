package com.parentsgowork.server.web.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {
    // 회원 탈퇴 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteUserResponseDTO {
        private String name;
        private String message; // ex) 회원 탈퇴가 완료되었습니다
    }
}
