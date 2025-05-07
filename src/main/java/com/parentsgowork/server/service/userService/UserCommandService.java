package com.parentsgowork.server.service.userService;

import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.web.dto.TokenDTO.TokenResponseDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserResponseDTO;

public interface UserCommandService {
    TokenResponseDTO.TokenDTO signupEmail(UserRequestDTO.UserInfoDTO userInfoDTO);

    TokenResponseDTO.TokenDTO loginEmail(UserRequestDTO.EmailLoginDTO emailLoginDTO);

    void updatePassword(UserRequestDTO.PasswordDTO passwordDTO);
    UserResponseDTO.DeleteUserResponseDTO delete(Long userId);

    TokenResponseDTO.TokenDTO issueTokenAndSetRefreshToken(User user);

    TokenResponseDTO.TokenDTO performAuthentication(String email, String password);

    void setRefreshToken(String refreshToken, User user);

    void checkUserInactive(User user);
}
