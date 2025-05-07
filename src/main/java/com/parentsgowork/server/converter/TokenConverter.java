package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.RefreshToken;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;

import java.time.LocalDateTime;

public class TokenConverter {

    public static RefreshToken toRefreshToken(String refreshToken, LocalDateTime expiryDate, User user) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .expiryDate(expiryDate)
                .user(user)
                .build();
    }

    public static TokenResponseDTO.AccessTokenDTO toAccessTokenDTO(String accessToken) {
        return TokenResponseDTO.AccessTokenDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public static TokenResponseDTO.TokenDTO toTokenDTO(String accessToken, String refreshToken){
        return TokenResponseDTO.TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
