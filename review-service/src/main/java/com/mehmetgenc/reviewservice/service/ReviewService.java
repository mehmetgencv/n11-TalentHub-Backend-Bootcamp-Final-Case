package com.mehmetgenc.reviewservice.service;

import com.mehmetgenc.reviewservice.entity.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review);

    Review findById(Long reviewId);

    List<Review> findAllReviewsByUserId(Long userId);

    List<Review> findAllReviewsByRestaurantId(Long restaurantId);

    Boolean deleteById(Long reviewId);
}
