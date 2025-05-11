package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.AuthenticationCode;
import com.parentsgowork.server.domain.enums.CodeStatus;
import com.parentsgowork.server.web.dto.AuthDTO.AuthResponseDTO;

import java.time.LocalDateTime;

public class AuthenticationCodeConverter {
    public static AuthenticationCode toAuthenticationCode(String email, String authCode) {
        return AuthenticationCode.builder()
                .email(email)
                .code(authCode)
                .isVerified(false)
                .status(CodeStatus.ACTIVE)
                .expirationDate(LocalDateTime.now().plusMinutes(5)) // 유효기간 5분
                .build()
                ;
    }

    public static AuthResponseDTO.SendAuthEmailResponseDTO toSendAuthCodeResponse(AuthenticationCode authCode) {
        return AuthResponseDTO.SendAuthEmailResponseDTO.builder()
                .email(authCode.getEmail())
                .message("이메일로 인증번호가 전송되었습니다.")
                .code(authCode.getCode())
                .expiration(authCode.getExpirationDate())
                .build()
                ;
    }

    public static AuthResponseDTO.VerifyAuthCodeResponseDTO toVerifiedAuthCodeResponse(AuthenticationCode authCode) {
        return AuthResponseDTO.VerifyAuthCodeResponseDTO.builder()
                .message("인증이 완료되었습니다.")
                .build()
                ;
    }
}
