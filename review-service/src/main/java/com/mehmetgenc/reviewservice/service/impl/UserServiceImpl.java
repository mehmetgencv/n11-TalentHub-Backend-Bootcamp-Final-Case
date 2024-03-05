package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.exception.UserNotFoundException;
import com.mehmetgenc.reviewservice.repository.UserRepository;
import com.mehmetgenc.reviewservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        // check if user exists
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public String delete(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted";
    }


}
