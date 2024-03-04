package com.mehmetgenc.reviewservice.repository;

import com.mehmetgenc.reviewservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
