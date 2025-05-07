package com.parentsgowork.server.converter;

import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.web.dto.UserDTO.UserRequestDTO;
import com.parentsgowork.server.web.dto.UserDTO.UserResponseDTO;

import static com.parentsgowork.server.domain.enums.Role.USER;
import static com.parentsgowork.server.domain.enums.UserStatus.ACTIVE;

public class UserConverter {
    public static User toUser(UserRequestDTO.UserInfoDTO userInfoDTO) {
        return User.builder()
                .name(userInfoDTO.getName())
                .primaryEmail(userInfoDTO.getEmail())
                .password(userInfoDTO.getPassword())
                .age(userInfoDTO.getAge())
                .gender(userInfoDTO.getGender())
                .region(userInfoDTO.getRegion())
                .job(userInfoDTO.getJob())
                .career(userInfoDTO.getCareer())
                .finalEdu(userInfoDTO.getFinalEdu())
                .role(USER)
                .status(ACTIVE)
                .build();
    }

    public static UserResponseDTO.DeleteUserResponseDTO toDeletedUser(User deleteUser) {
        return UserResponseDTO.DeleteUserResponseDTO.builder()
                .name(deleteUser.getName())
                .message("회원탈퇴가 완료되었습니다.")
                .build();
    }
}
