package com.mehmetgenc.reviewservice.repository;

import com.mehmetgenc.reviewservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);
}

