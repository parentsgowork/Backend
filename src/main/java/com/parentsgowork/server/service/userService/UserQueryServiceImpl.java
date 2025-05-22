package com.parentsgowork.server.service.userService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.UserConverter;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.web.dto.UserDTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.parentsgowork.server.domain.enums.UserStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO.UserPageInfoDTO getUserPageInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        return UserConverter.toGetUserInfo(user);
    }

    @Override
    public boolean isUserInactive(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        return user.getStatus() == INACTIVE;
    }
}
