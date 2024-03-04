package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

import java.time.LocalDate;

public record UserSaveRequest(String name,
                              String surname,
                              String email,
                              String password,
                              Gender gender,
                              Double latitude,
                              Double longitude) {
}