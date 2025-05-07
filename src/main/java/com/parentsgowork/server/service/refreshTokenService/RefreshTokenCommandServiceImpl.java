package com.parentsgowork.server.service.refreshTokenService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.AuthHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.TokenConverter;
import com.parentsgowork.server.domain.CustomUserDetails;
import com.parentsgowork.server.domain.RefreshToken;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.RefreshTokenRepository;
import com.parentsgowork.server.service.userService.CustomUserDetailsService;
import com.parentsgowork.server.util.JwtTokenUtil;
import com.parentsgowork.server.web.dto.TokenDTO.TokenRequestDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.parentsgowork.server.converter.TokenConverter.toAccessTokenDTO;
import static com.parentsgowork.server.domain.enums.UserStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class RefreshTokenCommandServiceImpl implements RefreshTokenCommandService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public RefreshToken createRefreshToken(String refreshToken, User user) {
        Date expiryDate = jwtTokenUtil.parseClaims(refreshToken).getExpiration();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault());

        RefreshToken refreshTokenEntity = TokenConverter.toRefreshToken(refreshToken, localDateTime, user);

        return refreshTokenRepository.save(refreshTokenEntity);
    }

    @Transactional(noRollbackFor = AuthHandler.class)
    @Override
    public TokenResponseDTO.AccessTokenDTO reissueToken(TokenRequestDTO.ReissueDTO reissueDTO) {
        RefreshToken storedRefreshToken = refreshTokenRepository.findByRefreshToken(reissueDTO.getRefreshToken()) //요청한 refresh token 이 database 에 존재하는지 확인
                .orElseThrow(() -> new AuthHandler(ErrorStatus.REFRESH_TOKEN_NOT_FOUND));

        if (storedRefreshToken.getUser().getStatus() == INACTIVE)   //탈퇴한 회원은 accessToken 재발급 하지 않음
            throw new UserHandler(ErrorStatus.USER_STATUS_INACTIVE);

        if (storedRefreshToken.getExpiryDate().isBefore(LocalDateTime.now())) { //refresh token 이 만료되었는지 확인
            storedRefreshToken.getUser().deleteRefreshToken();
            throw new AuthHandler(ErrorStatus.EXPIRED_TOKEN);
        }

        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(storedRefreshToken.getUser().getPrimaryEmail());

        String accessToken = jwtTokenUtil.generateAccessToken(customUserDetails); //액세스 토큰 생성

        return toAccessTokenDTO(accessToken);

    }

}
