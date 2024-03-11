package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import com.mehmetgenc.reviewservice.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewControllerContractImplTest {

    @Mock
    private ReviewService mockReviewService;

    @InjectMocks
    private ReviewControllerContractImpl reviewControllerContractImplUnderTest;


    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {

        reviewControllerContractImplUnderTest = new ReviewControllerContractImpl(mockReviewService);
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void testSave() {
        // given
        final ReviewSaveRequest reviewSaveRequest = exampleReviewSaveRequest();
        Review review = new Review();
        review.setId(1L);
        review.setComment(reviewSaveRequest.comment());
        review.setRate(reviewSaveRequest.rate());


        // when
        when(mockReviewService.save(any(ReviewSaveRequest.class))).thenReturn(review);
        ReviewDTO result = reviewControllerContractImplUnderTest.save(reviewSaveRequest);

        // then
        assertEquals(reviewSaveRequest.comment(), result.comment());
        assertEquals(reviewSaveRequest.rate(), result.rate());
    }

    @Test
    void testFindById() {

        // given
        Review sampleReview = exampleReview();
        when(mockReviewService.findById(1L)).thenReturn(sampleReview);

        // when
        ReviewDTO result = reviewControllerContractImplUnderTest.findById(1L);

        // then
        assertEquals(sampleReview.getComment(), result.comment());
        assertEquals(sampleReview.getRate(), result.rate());
    }

    @Test
    void testFindAllReviewsByUserId() {
        // given
        Review sampleReview = exampleReview();
        List<Review> reviews = Collections.singletonList(sampleReview);
        when(mockReviewService.findAllReviewsByUserId(1L)).thenReturn(reviews);

        // when
        List<ReviewDTO> result = reviewControllerContractImplUnderTest.findAllReviewsByUserId(1L);

        // then
        assertEquals(sampleReview.getComment(), result.get(0).comment());
        assertEquals(sampleReview.getRate(), result.get(0).rate());
    }

    @Test
    void testFindAllReviewsByRestaurantId() {
       // given
            Review sampleReview = exampleReview();
            List<Review> reviews = Collections.singletonList(sampleReview);
            when(mockReviewService.findAllReviewsByRestaurantId(1L)).thenReturn(reviews);

            // when
            List<ReviewDTO> result = reviewControllerContractImplUnderTest.findAllReviewsByRestaurantId(1L);

            // then
            assertEquals(sampleReview.getComment(), result.get(0).comment());
            assertEquals(sampleReview.getRate(), result.get(0).rate());
    }

    @Test
    void testDeleteById() {
        // given
        Boolean expectedResultBoolean = true;
        when(mockReviewService.deleteById(1L)).thenReturn(expectedResultBoolean);

        // when
        Boolean result = reviewControllerContractImplUnderTest.deleteById(1L);

        // then
        assertEquals(expectedResultBoolean, result);
    }

    @Test
    void testUpdateComment() {
        // given
        String newComment = "New comment";
        Review sampleReview = exampleReview();
        when(mockReviewService.findById(1L)).thenReturn(sampleReview);
        when(mockReviewService.save(sampleReview)).thenReturn(sampleReview);

        // when
        ReviewDTO result = reviewControllerContractImplUnderTest.updateComment(1L, newComment);

        // then
        assertEquals(newComment, result.comment());

    }

    @Test
    void testUpdateRating() {
        // given
        Rate newRate = Rate.FOUR;
        Review sampleReview = exampleReview();
        when(mockReviewService.findById(1L)).thenReturn(sampleReview);
        when(mockReviewService.save(sampleReview)).thenReturn(sampleReview);

        // when
        ReviewDTO result = reviewControllerContractImplUnderTest.updateRating(1L, newRate);

        // then
        assertEquals(newRate, result.rate());
    }

    @Test
    void testUpdateReview() {
        // given

        ReviewUpdateRequest reviewUpdateRequest = exampleReviewUpdateRequest();
        Review sampleReview = objectMapper.convertValue(exampleReview(), Review.class);
        when(mockReviewService.findById(1L)).thenReturn(sampleReview);

        when(mockReviewService.save(sampleReview)).thenReturn(sampleReview);

        // when
        ReviewDTO result = reviewControllerContractImplUnderTest.updateReview(1L, reviewUpdateRequest);

        // then
        assertEquals(reviewUpdateRequest.comment(), result.comment());
        assertEquals(reviewUpdateRequest.rate(), result.rate());

    }

    @Test
    void testSaveBatch() {
        // given
        ReviewSaveRequest reviewSaveRequest = exampleReviewSaveRequest();
        List<ReviewSaveRequest> reviewSaveRequestList = Collections.singletonList(reviewSaveRequest);
        Review review = new Review();
        review.setId(1L);
        review.setComment(reviewSaveRequest.comment());
        review.setRate(reviewSaveRequest.rate());
        List<Review> reviews = Collections.singletonList(review);
        when(mockReviewService.save(reviewSaveRequest)).thenReturn(review);

        // when
        List<ReviewDTO> result = reviewControllerContractImplUnderTest.saveBatch(reviewSaveRequestList);

        // then

        assertEquals(reviewSaveRequest.comment(), result.get(0).comment());
    }

    private Review exampleReview() {
        Review review = new Review();
        review.setId(1L);
        review.setComment("exampleReviewComment");
        review.setRate(Rate.FIVE);
        return review;
    }

    private ReviewSaveRequest exampleReviewSaveRequest() {
        return new ReviewSaveRequest(1L,1L,"exampleReviewSaveRequestComment", Rate.ONE);
    }

    private ReviewUpdateRequest exampleReviewUpdateRequest() {
        return new ReviewUpdateRequest("exampleReviewUpdateRequestComment", Rate.TWO);
    }

}
