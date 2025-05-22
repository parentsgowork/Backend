package com.parentsgowork.server.web.dto.UserDTO;

import com.parentsgowork.server.domain.enums.FinalEdu;
import com.parentsgowork.server.domain.enums.Gender;
import com.parentsgowork.server.domain.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {

    // 마이페이지 회원 조회 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPageInfoDTO {
        private Long id;
        private String name;
        private int age;
        private Gender gender;
        private Region region;
        private String job;
        private Integer career;
        private FinalEdu finalEdu;
    }

    // 회원 탈퇴 응답 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteUserResponseDTO {
        private String name;
        private String message; // ex) 회원 탈퇴가 완료되었습니다
    }
}
