package com.parentsgowork.server.service.refreshTokenService;

import com.parentsgowork.server.domain.RefreshToken;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.web.dto.TokenDTO.TokenRequestDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;

public interface RefreshTokenCommandService {
    RefreshToken createRefreshToken(String refreshToken, User user);

    TokenResponseDTO.AccessTokenDTO reissueToken(TokenRequestDTO.ReissueDTO reissueDTO);

}
