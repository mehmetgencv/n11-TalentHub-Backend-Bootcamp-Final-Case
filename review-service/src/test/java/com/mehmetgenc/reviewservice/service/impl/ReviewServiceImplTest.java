package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.controller.contract.impl.RestaurantControllerContractImpl;
import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import com.mehmetgenc.reviewservice.entity.enums.Rate;
import com.mehmetgenc.reviewservice.exception.ReviewNotFoundException;
import com.mehmetgenc.reviewservice.repository.ReviewRepository;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository mockReviewRepository;
    @Mock
    private UserService mockUserService;
    @Mock
    private RestaurantControllerContractImpl mockRestaurantControllerContract;

    private ReviewServiceImpl reviewServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        reviewServiceImplUnderTest = new ReviewServiceImpl(mockReviewRepository, mockUserService,
                mockRestaurantControllerContract);
    }

    @Test
    void testSaveWithReview() {
        // given
        final Review review = createExampleReview();
        when(mockReviewRepository.save(any(Review.class))).thenReturn(review);

        // when
        final Review result = reviewServiceImplUnderTest.save(review);

        // then
        assertThat(result).isEqualTo(review);
    }

    @Test
    void testSaveWithReviewSaveRequest() {
        // given
        final ReviewSaveRequest reviewSaveRequest = createExampleReviewSaveRequest();
        Long userId = reviewSaveRequest.userId();
        Review review = new Review();
        review.setComment(reviewSaveRequest.comment());
        review.setRate(reviewSaveRequest.rate());
        review.setRestaurantId(reviewSaveRequest.restaurantId());
        User user = new User();
        when(mockUserService.findById(userId)).thenReturn(user);
        review.setUser(user);
        when(mockReviewRepository.save(any(Review.class))).thenReturn(review);

        // when
        final Review result = reviewServiceImplUnderTest.save(reviewSaveRequest);

        // then
        assertThat(result).isEqualTo(review);
    }

    @Test
    void testFindById() {
        // given
        final Review review = createExampleReview();
        when(mockReviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // when
        final Review result = reviewServiceImplUnderTest.findById(1L);

        // then
        assertThat(result).isEqualTo(review);
    }

    @Test
    void testFindById_shouldThrowReviewNotFoundException() {
        // Setup
        when(mockReviewRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> reviewServiceImplUnderTest.findById(1L)).isInstanceOf(ReviewNotFoundException.class);
    }

    @Test
    void testFindAllReviewsByUserId() {
        // given
        final Review review = createExampleReview();
        when(mockReviewRepository.findAllByUserId(1L)).thenReturn(List.of(review));

        // when
        final List<Review> result = reviewServiceImplUnderTest.findAllReviewsByUserId(1L);

        // then
        assertThat(result).isEqualTo(List.of(review));
    }

    @Test
    void testFindAllReviewsByUserId_ReviewRepositoryReturnsNoItems() {
        // given
        when(mockReviewRepository.findAllByUserId(1L)).thenReturn(Collections.emptyList());

        // when
        final List<Review> result = reviewServiceImplUnderTest.findAllReviewsByUserId(1L);

        // then
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindAllReviewsByRestaurantId() {
        // given
        final Review review = createExampleReview();
        when(mockReviewRepository.findAllByRestaurantId(1L)).thenReturn(List.of(review));

        // when
        final List<Review> result = reviewServiceImplUnderTest.findAllReviewsByRestaurantId(1L);

        // then
        assertThat(result).isEqualTo(List.of(review));
    }

    @Test
    void testFindAllReviewsByRestaurantId_ReviewRepositoryReturnsNoItems() {
        // given
        when(mockReviewRepository.findAllByRestaurantId(1L)).thenReturn(Collections.emptyList());

        // when
        final List<Review> result = reviewServiceImplUnderTest.findAllReviewsByRestaurantId(1L);

        // then
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteById() {
        // when
        final Boolean result = reviewServiceImplUnderTest.deleteById(1L);

        // then
        assertThat(result).isTrue();
        verify(mockReviewRepository).deleteById(1L);
    }



    private ReviewSaveRequest createExampleReviewSaveRequest() {
        return new ReviewSaveRequest(
                1L,
                1L,
                "comment",
                Rate.FOUR);
    }

    private Review createExampleReview() {
        Review review = new Review();
        review.setId(1L);
        review.setComment("comment");
        review.setRate(Rate.FOUR);
        review.setRestaurantId(1L);
        review.setUser(new User());
        return review;
    }

}
