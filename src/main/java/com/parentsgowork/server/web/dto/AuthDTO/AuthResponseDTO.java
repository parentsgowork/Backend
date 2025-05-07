package com.parentsgowork.server.web.dto.AuthDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AuthResponseDTO {

    // 이메일 인증 전송 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendAuthEmailResponseDTO {
        private String email;
        private String message; // ex) 인증 이메일이 발송되었습니다
        private String code;
        private LocalDateTime expiration;
    }

    // 인증 코드 확인 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerifyAuthCodeResponseDTO {
        private String message; // ex) 인증이 완료되었습니다, 인증번호가 올바르지 않습니다
    }
}
