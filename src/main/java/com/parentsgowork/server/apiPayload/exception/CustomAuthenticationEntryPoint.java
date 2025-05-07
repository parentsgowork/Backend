package com.parentsgowork.server.apiPayload.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parentsgowork.server.apiPayload.code.ErrorReasonDTO;
import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.parentsgowork.server.apiPayload.code.status.ErrorStatus._BAD_REQUEST;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorStatus errorStatus = (ErrorStatus) request.getAttribute("errorStatus");

        if (errorStatus == null)
            errorStatus = _BAD_REQUEST;

        ErrorReasonDTO errorReasonDTO = errorStatus.getReasonHttpStatus();

        response.setStatus(errorReasonDTO.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(errorReasonDTO));
    }
}