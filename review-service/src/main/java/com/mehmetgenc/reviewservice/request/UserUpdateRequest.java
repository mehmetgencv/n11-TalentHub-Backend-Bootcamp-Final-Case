package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

public record UserUpdateRequest(String name,
                                String surname,
                                String email,
                                String password,
                                Gender gender,
                                Double latitude,
                                Double longitude) {
}
