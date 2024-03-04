package com.mehmetgenc.reviewservice.mapper;


import com.mehmetgenc.reviewservice.dto.ReviewDTO;
import com.mehmetgenc.reviewservice.entity.Review;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.request.ReviewSaveRequest;
import com.mehmetgenc.reviewservice.request.ReviewUpdateRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);


    Review convertToReview(ReviewSaveRequest productSaveRequest);

    ReviewDTO convertToReviewDto(Review review);

    List<ReviewDTO> convertToReviewDtos(List<Review> reviews);

    @Mapping(target = "id", ignore = true)
    void updateReviewFields(@MappingTarget Review review, ReviewUpdateRequest request);
}
