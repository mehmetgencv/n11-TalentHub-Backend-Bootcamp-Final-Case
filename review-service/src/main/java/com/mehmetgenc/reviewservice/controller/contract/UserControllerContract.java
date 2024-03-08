package com.mehmetgenc.reviewservice.controller.contract;

import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;

import java.util.List;

public interface UserControllerContract {
    UserDTO save(UserSaveRequest userSaveRequest);

    UserDTO findById(Long userId);

    List<UserDTO> findAll();

    String delete(Long userId);

    UserDTO update(Long userId, UserUpdateRequest userUpdateRequest);

    List<UserDTO> saveBatch(List<UserSaveRequest> userSaveRequestList);
}
