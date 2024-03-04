package com.mehmetgenc.reviewservice.controller.contract;

import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;

import java.util.List;

public interface ReviewControllerContract {
    ReviewDTO save(ReviewSaveRequest productSaveRequest);

    ReviewDTO findById(Long reviewId);

    List<ReviewDTO> findAllReviewsByUserId(Long userId);

    List<ReviewDTO> findAllReviewsByRestaurantId(Long restaurantId);

    Boolean deleteById(Long reviewId);

    ReviewDTO updateComment(Long reviewId, String comment);

    ReviewDTO updateRating(Long reviewId, Rate rating);

    ReviewDTO updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest);
}
