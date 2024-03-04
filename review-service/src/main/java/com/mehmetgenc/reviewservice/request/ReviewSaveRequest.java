package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Rate;

public record ReviewSaveRequest(
        Long userId,
        Long restaurantId,
        String comment,
        Rate rate) {
}
