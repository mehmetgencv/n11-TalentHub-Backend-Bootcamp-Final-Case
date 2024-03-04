package com.mehmetgenc.reviewservice.repository;

import com.mehmetgenc.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserId(Long userId);

    List<Review> findAllByRestaurantId(Long restaurantId);
}
