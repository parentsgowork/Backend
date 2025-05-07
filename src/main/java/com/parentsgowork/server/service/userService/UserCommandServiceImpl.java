package com.parentsgowork.server.service.userService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.UserConverter;
import com.parentsgowork.server.domain.CustomUserDetails;
import com.parentsgowork.server.domain.RefreshToken;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.service.refreshTokenService.RefreshTokenCommandService;
import com.parentsgowork.server.util.JwtTokenUtil;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.parentsgowork.server.apiPayload.code.status.ErrorStatus._BAD_REQUEST;
import static com.parentsgowork.server.domain.enums.UserStatus.INACTIVE;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RefreshTokenCommandService refreshTokenCommandService;
    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponseDTO.TokenDTO signupEmail(UserRequestDTO.UserInfoDTO userInfoDTO) {
        //인증 받지 않았을 때 예외 처리
        if (userInfoDTO.getIsVerified() == null || !userInfoDTO.getIsVerified())
            throw new UserHandler(ErrorStatus.EMAIL_NOT_VERIFIED);

        String email = userInfoDTO.getEmail();

        // 이미 이메일 가입한 회원
        if (userRepository.existsByPrimaryEmail(email)) {
            String password = userRepository.findPasswordByPrimaryEmail(email);

            if (password != null) {
                throw new UserHandler(ErrorStatus.EMAIL_ALREADY_EXISTS);
            }

            User user = userRepository.findByPrimaryEmail(email)
                    .orElseThrow(() -> new UserHandler(_BAD_REQUEST));

            return issueTokenAndSetRefreshToken(user);
        }

        // 최초 이메일 회원가입
        User newUser = UserConverter.toUser(userInfoDTO);
        newUser.encodePassword(passwordEncoder.encode(newUser.getPassword()));

        // User 엔티티 저장 및 AT/RT 발급
        return issueTokenAndSetRefreshToken(userRepository.save(newUser));

    }

    @Override
    public TokenResponseDTO.TokenDTO loginEmail(UserRequestDTO.EmailLoginDTO emailLoginDTO) {
        String email = emailLoginDTO.getEmail(); //사용자가 입력한 email
        String password = emailLoginDTO.getPassword(); //사용자가 입력한 password

        try {
            User user = userRepository.findByPrimaryEmail(email)
                    .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

            // 탈퇴한 사용자인지 확인
            checkUserInactive(user);

            // 인증 수행 및 토큰 생성 및 저장
            TokenResponseDTO.TokenDTO tokenDTO = performAuthentication(email, password);
            setRefreshToken(tokenDTO.getRefreshToken(), user);

            return tokenDTO;
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new UserHandler(ErrorStatus.USER_NOT_FOUND); //사용자가 입력한 email 또는 password 데이터가 데이터베이스에 없을 때 예외 처리
        }
    }

    @Override
    public void updatePassword(UserRequestDTO.PasswordDTO passwordDTO) {
        //인증 받지 않았을 때 예외 처리
        if (passwordDTO.getIsVerified() == null || !passwordDTO.getIsVerified())
            throw new UserHandler(ErrorStatus.EMAIL_NOT_VERIFIED);
            //인증 확인 받았을 때 비밀번호 일치 확인 후 비밀번호 변경
        else {
            if (!passwordDTO.getPassword().equals(passwordDTO.getPasswordCheck()))
                throw new UserHandler(ErrorStatus.PASSWORD_NOT_MATCH);
            else {
                //사용자 정보 없을 때 예외 처리
                String email = passwordDTO.getEmail();
                Optional<User> user = userRepository.findByPrimaryEmail(email);
                user.orElseThrow(() ->
                        new UserHandler(ErrorStatus.USER_NOT_FOUND));

                //탈퇴한 회원일 때 예외 처리
                if (user.get().getStatus() == INACTIVE)
                    throw new UserHandler(ErrorStatus.USER_STATUS_INACTIVE);

                //사용자 비밀번호 변경
                String hashedPassword = passwordEncoder.encode(passwordDTO.getPassword());
                user.get().encodePassword(hashedPassword); //사용자 비밀번호 변경
            }
        }
    }

    /**
     * AT/RT 발급 및 RT DB 저장
     */
    @Override
    public TokenResponseDTO.TokenDTO issueTokenAndSetRefreshToken(User user) {
        TokenResponseDTO.TokenDTO tokenDTO = jwtTokenUtil.generateToken(
                customUserDetailsService.loadUserByUsername(user.getPrimaryEmail()));

        RefreshToken refreshTokenEntity = refreshTokenCommandService.createRefreshToken(tokenDTO.getRefreshToken(), user);
        user.setRefreshToken(refreshTokenEntity);

        return tokenDTO;
    }

    @Override
    public void setRefreshToken(String refreshToken, User user) {
        RefreshToken refreshTokenEntity = refreshTokenCommandService.createRefreshToken(refreshToken, user);
        user.setRefreshToken(refreshTokenEntity);
    }

    @Override
    public TokenResponseDTO.TokenDTO performAuthentication(String email, String password) {
        //인증되지 않은 상태의 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        //인증 성공 시 인증된 상태의 Authentication 객체 반환, 인증 실패 시 예외 던짐
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //인증 성공 시 JWT 토큰 생성
        TokenResponseDTO.TokenDTO tokenDTO = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());

        return tokenDTO;
    }

    @Override
    public void checkUserInactive(User user){
        if (user.getStatus() == INACTIVE)
            throw new UserHandler(ErrorStatus.USER_STATUS_INACTIVE);
    }
}
