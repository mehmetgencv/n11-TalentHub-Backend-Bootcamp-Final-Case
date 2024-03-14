package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.ReviewControllerContract;
import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.general.KafkaProducerService;
import com.mehmetgenc.reviewservice.general.RestResponse;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@Validated
@Tag(name = "Review Controller", description = "Review Management")
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    private final ReviewControllerContract reviewControllerContract;
    private final KafkaProducerService kafkaProducerService;

    public ReviewController(ReviewControllerContract reviewControllerContract, KafkaProducerService kafkaProducerService) {
        this.reviewControllerContract = reviewControllerContract;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    @Operation(summary = "Create a new review", description = "Creates a new review with the provided details")
    public ResponseEntity<RestResponse<ReviewDTO>> save(@RequestBody @Valid ReviewSaveRequest productSaveRequest){
        ReviewDTO reviewDTO = reviewControllerContract.save(productSaveRequest);
        kafkaProducerService.sendMessage("infoLog", "Review saved with id: " + reviewDTO.id());
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "Find review by Review ID", description = "Returns a single review based on the provided ID")
    public ResponseEntity<RestResponse<ReviewDTO>> findById(@PathVariable @Positive Long reviewId){
        ReviewDTO reviewDTO = reviewControllerContract.findById(reviewId);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @GetMapping("/findAllReviewsByUserId/{userId}")
    @Operation(summary = "Find all reviews by user ID", description = "Returns all reviews based on the provided user ID")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviewsByUserId(@PathVariable @Positive Long userId){
        List<ReviewDTO> reviewDTOS = reviewControllerContract.findAllReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(reviewDTOS));

    }

    @GetMapping("/findAllReviewsByRestaurantId/{restaurantId}")
    @Operation(summary = "Find all reviews by restaurant ID", description = "Returns all reviews based on the provided restaurant ID")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviewsByRestaurantId(@PathVariable @Positive Long restaurantId){
        List<ReviewDTO> reviewDTOS = reviewControllerContract.findAllReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(RestResponse.of(reviewDTOS));

    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete review by Review ID", description = "Deletes a single review based on the provided ID")
    public ResponseEntity<RestResponse<Boolean>> deleteById(@PathVariable @Positive Long reviewId){
        Boolean result = reviewControllerContract.deleteById(reviewId);
        kafkaProducerService.sendMessage("infoLog", "Review deleted with id: " + reviewId);
        return ResponseEntity.ok(RestResponse.of(result));

    }

    @PatchMapping("/updateComment/{reviewId}")
    @Operation(summary = "Update comment by Review ID", description = "Updates a single comment based on the provided ID")
    public ResponseEntity<RestResponse<ReviewDTO>> updateComment(@PathVariable @Positive Long reviewId, @RequestBody String comment){
        ReviewDTO reviewDTO = reviewControllerContract.updateComment(reviewId, comment);
        kafkaProducerService.sendMessage("infoLog", "Review updated with id: " + reviewId);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @PatchMapping("/updateRating/{reviewId}")
    @Operation(summary = "Update rating by Review ID", description = "Updates a single rating based on the provided ID")
    public ResponseEntity<RestResponse<ReviewDTO>> updateRating(@PathVariable @Positive Long reviewId, @RequestBody @NotNull Rate rating){
        ReviewDTO reviewDTO = reviewControllerContract.updateRating(reviewId, rating);
        kafkaProducerService.sendMessage("infoLog", "Review updated with id: " + reviewId);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @PutMapping("/updateReview/{reviewId}")
    @Operation(summary = "Update review by Review ID", description = "Updates a single review based on the provided ID")
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(@PathVariable @Positive Long reviewId, @RequestBody @Valid ReviewUpdateRequest reviewUpdateRequest){
        ReviewDTO reviewDTO = reviewControllerContract.updateReview(reviewId, reviewUpdateRequest);
        kafkaProducerService.sendMessage("infoLog", "Review updated with id: " + reviewId);
        return ResponseEntity.ok(RestResponse.of(reviewDTO));

    }

    @PostMapping("/saveBatchReviews")
    @Operation(summary = "Create new reviews", description = "Creates new reviews with the provided details")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> saveBatchReviews(@RequestBody List<ReviewSaveRequest> reviewSaveRequestList){
        List<ReviewDTO> reviewDTOS = reviewControllerContract.saveBatch(reviewSaveRequestList);
        return ResponseEntity.ok(RestResponse.of(reviewDTOS));

    }
}
