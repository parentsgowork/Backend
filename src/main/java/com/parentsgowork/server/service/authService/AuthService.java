package com.parentsgowork.server.service.authService;

import com.parentsgowork.server.domain.enums.AuthType;
import com.parentsgowork.server.web.dto.AuthDTO.AuthRequestDTO;
import com.parentsgowork.server.web.dto.AuthDTO.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO.SendAuthEmailResponseDTO sendAuthEmail(AuthType type, AuthRequestDTO.SendAuthEmailRequestDTO request);

    AuthResponseDTO.VerifyAuthCodeResponseDTO verifyAuthCode(AuthRequestDTO.VerifyAuthCodeRequestDTO request);
}
