package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.exception.ReviewNotFoundException;
import com.mehmetgenc.reviewservice.repository.ReviewRepository;
import com.mehmetgenc.reviewservice.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
    }

    @Override
    public List<Review> findAllReviewsByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }

    @Override
    public List<Review> findAllReviewsByRestaurantId(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public Boolean deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return true;
    }
}
