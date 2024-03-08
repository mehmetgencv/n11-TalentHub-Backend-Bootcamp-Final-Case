package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.controller.contract.impl.RestaurantControllerContractImpl;
import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.exception.ReviewNotFoundException;
import com.mehmetgenc.reviewservice.mapper.ReviewMapper;
import com.mehmetgenc.reviewservice.repository.ReviewRepository;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.service.ReviewService;
import com.mehmetgenc.reviewservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RestaurantControllerContractImpl restaurantControllerContract;


    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, RestaurantControllerContractImpl restaurantControllerContract) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.restaurantControllerContract = restaurantControllerContract;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
    @Override
    public Review save(ReviewSaveRequest reviewSaveRequest) {
        Review review = ReviewMapper.INSTANCE.convertToReview(reviewSaveRequest);
        Long userId = reviewSaveRequest.userId();
        User user = userService.findById(userId);
        review.setUser(user);
        Double rate = reviewSaveRequest.rate().getValue();
        restaurantControllerContract.updateRate(reviewSaveRequest.restaurantId(), rate);
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
