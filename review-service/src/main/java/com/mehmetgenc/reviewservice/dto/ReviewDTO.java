package com.mehmetgenc.reviewservice.dto;

import com.mehmetgenc.reviewservice.entity.enums.Rate;

public record ReviewDTO(
        Long id,
        Long userId,
        Long restaurantId,
        String comment,
        Rate rate) {
}
