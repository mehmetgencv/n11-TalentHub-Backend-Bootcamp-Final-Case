package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Rate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record ReviewSaveRequest(
        @NotBlank(message = "User Id is mandatory")
        @Positive(message = "User Id must be positive")
        Long userId,
        @NotBlank(message = "Restaurant Id is mandatory")
        @Positive(message = "Restaurant Id must be positive")
        Long restaurantId,
        String comment,
        @NotBlank(message = "Rate is mandatory")
        Rate rate) {
}
