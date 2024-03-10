package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Rate;

import javax.validation.constraints.NotNull;

public record ReviewUpdateRequest(
        String comment,
        @NotNull(message = "Rate is mandatory")
        Rate rate) {
}
