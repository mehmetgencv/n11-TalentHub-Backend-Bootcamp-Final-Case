package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.ReviewControllerContract;
import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.general.RestResponse;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewControllerContract reviewControllerContract;

    public ReviewController(ReviewControllerContract reviewControllerContract) {
        this.reviewControllerContract = reviewControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<ReviewDTO>> save(@RequestBody ReviewSaveRequest productSaveRequest){
        ReviewDTO reviewDTO = reviewControllerContract.save(productSaveRequest);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<RestResponse<ReviewDTO>> findById(@PathVariable Long reviewId){
        ReviewDTO reviewDTO = reviewControllerContract.findById(reviewId);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @GetMapping("/findAllReviewsByUserId/{userId}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviewsByUserId(@PathVariable Long userId){
        List<ReviewDTO> reviewDTOS = reviewControllerContract.findAllReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(reviewDTOS));

    }

    @GetMapping("/findAllReviewsByRestaurantId/{restaurantId}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviewsByRestaurantId(@PathVariable Long restaurantId){
        List<ReviewDTO> reviewDTOS = reviewControllerContract.findAllReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(RestResponse.of(reviewDTOS));

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<RestResponse<Boolean>> deleteById(@PathVariable Long reviewId){
        Boolean result = reviewControllerContract.deleteById(reviewId);
        return ResponseEntity.ok(RestResponse.of(result));

    }

    @PatchMapping("/updateComment/{reviewId}")
    public ResponseEntity<RestResponse<ReviewDTO>> updateComment(@PathVariable Long reviewId, @RequestBody String comment){
        ReviewDTO reviewDTO = reviewControllerContract.updateComment(reviewId, comment);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @PatchMapping("/updateRating/{reviewId}")
    public ResponseEntity<RestResponse<ReviewDTO>> updateRating(@PathVariable Long reviewId, @RequestBody Rate rating){
        ReviewDTO reviewDTO = reviewControllerContract.updateRating(reviewId, rating);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @PutMapping("/updateReview/{reviewId}")
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateRequest reviewUpdateRequest){
        ReviewDTO reviewDTO = reviewControllerContract.updateReview(reviewId, reviewUpdateRequest);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }
}
