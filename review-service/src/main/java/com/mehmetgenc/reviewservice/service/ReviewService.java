package com.mehmetgenc.reviewservice.service;

import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    Review save(ReviewSaveRequest reviewSaveRequest);

    Review findById(Long reviewId);

    List<Review> findAllReviewsByUserId(Long userId);

    List<Review> findAllReviewsByRestaurantId(Long restaurantId);

    Boolean deleteById(Long reviewId);

    List<Review> saveBatch(List<Review> reviews);
}
