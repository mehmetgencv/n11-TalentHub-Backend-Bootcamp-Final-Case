package com.mehmetgenc.reviewservice.service;

import com.mehmetgenc.reviewservice.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long userId);

    List<User> findAll();

    String delete(Long userId);


    List<User> saveBatch(List<User> users);
}
