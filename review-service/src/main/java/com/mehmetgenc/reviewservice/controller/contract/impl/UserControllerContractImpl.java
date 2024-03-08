package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.mehmetgenc.reviewservice.controller.contract.UserControllerContract;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.mapper.UserMapper;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import com.mehmetgenc.reviewservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {
    private final UserService userService;

    @Override
    public UserDTO save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        user = userService.save(user);
        return UserMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    public UserDTO findById(Long userId) {
        User user = userService.findById(userId);
        return UserMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();

        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }

    @Override
    public String delete(Long userId) {
        return userService.delete(userId);
    }

    @Override
    public UserDTO update(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userService.findById(userId);
        UserMapper.INSTANCE.updateCustomerFields(user, userUpdateRequest);
        userService.save(user);
        return UserMapper.INSTANCE.convertToUserDto(user);
    }

    @Override
    public List<UserDTO> saveBatch(List<UserSaveRequest> userSaveRequestList) {
        List<User> users = UserMapper.INSTANCE.convertToUsers(userSaveRequestList);
        users = userService.saveBatch(users);
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }
}
