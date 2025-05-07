package com.parentsgowork.server.web.controller.specification;

import com.parentsgowork.server.apiPayload.ApiResponse;
import com.parentsgowork.server.domain.enums.AuthType;
import com.parentsgowork.server.web.dto.AuthDTO.AuthRequestDTO;
import com.parentsgowork.server.web.dto.AuthDTO.AuthResponseDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenRequestDTO;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthSpecification {

    @PostMapping("/signup")
    @SecurityRequirement(name = "")
    @Operation(summary = "이메일 회원가입 API", description = "약관 목록 입력할 때 termId 1~6 입력해 주세요. (termId 1~4는 필수 동의 항목입니다.)", security = @SecurityRequirement(name = ""))
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4001", description = "❌ 이메일 인증을 완료해주세요.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4003", description = "❌ 이미 존재하는 이메일입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TERM4001", description = "❌ 존재하지 않는 약관을 요청했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TERM4002", description = "❌ 필수 약관에 반드시 동의해야 합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TERM4003", description = "❌ 전체 약관 정보를 주세요.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ 회원가입 입력 형식이 맞지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<TokenResponseDTO.TokenDTO> signupEmail(@RequestBody @Valid UserRequestDTO.UserInfoDTO userInfoDTO);

    @PostMapping("/login")
    @Operation(summary = "이메일 로그인 API", description = "이메일 로그인 API 입니다. AccessToken, RefreshToken 이 모두 만료되어 토큰 재발급 API 에서 AccessToken 재발급이 불가능하면 로그인 화면으로 이동합니다.", security = @SecurityRequirement(name = ""))
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4002", description = "❌ 이메일 또는 패스워드가 일치하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4004", description = "❌ 탈퇴한 회원입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ 로그인 입력 형식이 맞지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<TokenResponseDTO.TokenDTO> loginEmail(@RequestBody @Valid UserRequestDTO.EmailLoginDTO emailLoginDTO);

    @PostMapping("/reissue")
    @Operation(summary = "토큰 재발급 API", description = "AccessToken 이 만료되었다는 401 에러 발생 시 토큰 재발급을 요청하는 API 입니다. 로그인 또는 회원가입 시 얻은 RefreshToken 을 Request Body 에 담아 요청해 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4002", description = "❌ 만료된 토큰입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4004", description = "❌ 탈퇴한 회원입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4007", description = "❌ 데이터베이스에서 refreshToken을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<TokenResponseDTO.AccessTokenDTO> reissueToken(@RequestBody @Valid TokenRequestDTO.ReissueDTO reissueDTO);

    @PostMapping("/email")
    @Operation(summary = "인증 메일 전송 API", description = "사용자에게 인증 메일을 전송하는 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4002", description = "❌ 이메일 또는 패스워드가 일치하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4003", description = "❌ 이미 존재하는 이메일입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4006", description = "❌ 이메일 인증 타입이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH5001", description = "❌ 이메일 전송에 실패했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH5002", description = "❌ 이메일 내용 인코딩에 실패했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<AuthResponseDTO.SendAuthEmailResponseDTO> sendAuthEmail(
            @RequestParam AuthType type,
            @RequestBody @Valid AuthRequestDTO.SendAuthEmailRequestDTO request
    );

    @PostMapping("/verification")
    @Operation(summary = "인증 번호 확인 API", description = "사용자가 입력한 인증 번호를 확인하는 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "⭕ SUCCESS"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4007", description = "❌ 유효한 인증번호가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH4008", description = "❌ 인증번호가 올바르지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "❌ BAD, 잘못된 요청", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<AuthResponseDTO.VerifyAuthCodeResponseDTO> verifyAuthCode(@RequestBody @Valid AuthRequestDTO.VerifyAuthCodeRequestDTO request);
}
