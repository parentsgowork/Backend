package com.parentsgowork.server.apiPayload.exception;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private final ErrorStatus errorStatus;

    public JwtAuthenticationException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
