package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

import java.time.LocalDate;

public record UserSaveRequest(String name,
                              String surname,
                              String email,
                              Gender gender,
                              Double latitude,
                              Double longitude) {
}