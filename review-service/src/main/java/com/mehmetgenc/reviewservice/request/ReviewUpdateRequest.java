package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Rate;

public record ReviewUpdateRequest(
        String comment,
        Rate rate) {
}
