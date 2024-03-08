package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.mehmetgenc.reviewservice.controller.contract.ReviewControllerContract;
import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.mapper.ReviewMapper;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import com.mehmetgenc.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewControllerContractImpl implements ReviewControllerContract {
    private final ReviewService reviewService;
    @Override
    public ReviewDTO save(ReviewSaveRequest reviewSaveRequest) {
        Review review = reviewService.save(reviewSaveRequest);
        return ReviewMapper.INSTANCE.convertToReviewDto(review);
    }

    @Override
    public ReviewDTO findById(Long reviewId) {
        Review review = reviewService.findById(reviewId);
        return ReviewMapper.INSTANCE.convertToReviewDto(review);
    }

    @Override
    public List<ReviewDTO> findAllReviewsByUserId(Long userId) {
        List<Review> reviews = reviewService.findAllReviewsByUserId(userId);
        return ReviewMapper.INSTANCE.convertToReviewDtos(reviews);
    }

    @Override
    public List<ReviewDTO> findAllReviewsByRestaurantId(Long restaurantId) {
        List<Review> reviews = reviewService.findAllReviewsByRestaurantId(restaurantId);
        return ReviewMapper.INSTANCE.convertToReviewDtos(reviews);
    }

    @Override
    public Boolean deleteById(Long reviewId) {
        return reviewService.deleteById(reviewId);
    }

    @Override
    public ReviewDTO updateComment(Long reviewId, String comment) {
        Review review = reviewService.findById(reviewId);
        review.setComment(comment);
        review = reviewService.save(review);
        return ReviewMapper.INSTANCE.convertToReviewDto(review);
    }

    @Override
    public ReviewDTO updateRating(Long reviewId, Rate rating) {
        Review review = reviewService.findById(reviewId);
        review.setRate(rating);
        review = reviewService.save(review);
        return ReviewMapper.INSTANCE.convertToReviewDto(review);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest) {
        Review review = reviewService.findById(reviewId);
        ReviewMapper.INSTANCE.updateReviewFields(review, reviewUpdateRequest);
        reviewService.save(review);
        return ReviewMapper.INSTANCE.convertToReviewDto(review);
    }

    @Override
    public List<ReviewDTO> saveBatch(List<ReviewSaveRequest> reviewSaveRequestList) {
        List<Review> reviews = reviewSaveRequestList.stream().map(reviewService::save).toList();
        return ReviewMapper.INSTANCE.convertToReviewDtos(reviews);
    }
}
