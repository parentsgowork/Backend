package com.parentsgowork.server.apiPayload.code.status;

import com.parentsgowork.server.apiPayload.code.BaseErrorCode;
import com.parentsgowork.server.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    //멤버 관련 에러
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "USER4001", "이메일 또는 패스워드가 일치하지 않습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER4002", "이미 존재하는 이메일입니다."),
    USER_STATUS_INACTIVE(HttpStatus.FORBIDDEN, "USER4003", "탈퇴한 회원입니다."),

    // 비밀번호 관련 에러
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "PWD4001", "비밀번호 확인이 일치하지 않습니다."),

    //인증 관련 에러
    EMAIL_NOT_VERIFIED(HttpStatus.BAD_REQUEST, "AUTH4001", "이메일 인증을 완료해주세요."),
    INVALID_AUTH_TYPE(HttpStatus.BAD_REQUEST, "AUTH4002", "이메일 인증 타입이 잘못되었습니다."),
    AUTH_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH4003", "유효한 인증번호가 없습니다."),
    AUTH_CODE_MISMATCH(HttpStatus.BAD_REQUEST, "AUTH4004", "인증번호가 올바르지 않습니다."),

    // 토큰 관련 에러
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4011", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4012", "만료된 토큰입니다. 토큰의 만료 시간이 지나 더 이상 유효하지 않습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4013", "지원하지 않는 토큰입니다. 서버가 처리할 수 없는 형식의 토큰입니다."),
    MALFORMED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4014", "손상된 토큰입니다. 토큰 구조가 올바르지 않거나, 일부 데이터가 손실되었습니다."),
    MISSING_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4015", "토큰이 제공되지 않았습니다."),
    INVALID_TOKEN_FORMAT(HttpStatus.UNAUTHORIZED, "AUTH4016", "잘못된 토큰 형식입니다. 토큰이 Base64 URL 인코딩 규칙을 따르지 않거나, 토큰 내부 JSON 구조가 잘못 되었습니다."),
    INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "AUTH4017", "토큰의 서명이 올바르지 않습니다. 서명이 서버에서 사용하는 비밀키와 일치하지 않습니다."),

    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH4020", "데이터베이스에서 refreshToken을 찾을 수 없습니다."),

    // 이메일 관련 에러
    EMAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH5001", "이메일 전송에 실패했습니다."),
    EMAIL_ENCODING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH5002", "이메일 내용 인코딩에 실패했습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
