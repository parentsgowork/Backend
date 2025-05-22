package com.parentsgowork.server.service.userService;

import com.parentsgowork.server.web.dto.UserDTO.UserResponseDTO;

public interface UserQueryService {

    UserResponseDTO.UserPageInfoDTO getUserPageInfo(Long userId);

    boolean isUserInactive(Long userId);
}
