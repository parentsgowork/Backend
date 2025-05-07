package com.parentsgowork.server.web.controller;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.domain.enums.AuthType;
import com.parentsgowork.server.service.authService.AuthService;
import com.parentsgowork.server.service.refreshTokenService.RefreshTokenCommandService;
import com.parentsgowork.server.service.userService.UserCommandService;
import com.parentsgowork.server.web.dto.AuthDTO.AuthRequestDTO;
import com.parentsgowork.server.web.dto.AuthDTO.AuthResponseDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenRequestDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserCommandService userCommandService;
    private final AuthService authService;
    private final RefreshTokenCommandService refreshTokenCommandService;

    @PostMapping("/login")
    public ApiResponse<TokenResponseDTO.TokenDTO> loginEmail(@RequestBody @Valid UserRequestDTO.EmailLoginDTO emailLoginDTO) {
        TokenResponseDTO.TokenDTO tokenDTO = userCommandService.loginEmail(emailLoginDTO);
        return ApiResponse.onSuccess(tokenDTO);
    }

    @PostMapping("/signup")
    public ApiResponse<TokenResponseDTO.TokenDTO> signupEmail(@RequestBody @Valid UserRequestDTO.UserInfoDTO userInfoDTO) {
        TokenResponseDTO.TokenDTO tokenDTO = userCommandService.signupEmail(userInfoDTO);
        return ApiResponse.onSuccess(tokenDTO);
    }

    @PostMapping("/reissue")
    public ApiResponse<TokenResponseDTO.AccessTokenDTO> reissueToken(@RequestBody @Valid TokenRequestDTO.ReissueDTO reissueDTO) {
        TokenResponseDTO.AccessTokenDTO accessTokenDTO = refreshTokenCommandService.reissueToken(reissueDTO);
        return ApiResponse.onSuccess(accessTokenDTO);
    }

    @PostMapping("/email")
    public ApiResponse<AuthResponseDTO.SendAuthEmailResponseDTO> sendAuthEmail(
            @RequestParam AuthType type,
            @RequestBody @Valid AuthRequestDTO.SendAuthEmailRequestDTO request) {
        AuthResponseDTO.SendAuthEmailResponseDTO result = authService.sendAuthEmail(type, request);

        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/verification")
    public ApiResponse<AuthResponseDTO.VerifyAuthCodeResponseDTO> verifyAuthCode(
            @RequestBody @Valid AuthRequestDTO.VerifyAuthCodeRequestDTO request) {
        AuthResponseDTO.VerifyAuthCodeResponseDTO result = authService.verifyAuthCode(request);

        return ApiResponse.onSuccess(result);
    }
}
