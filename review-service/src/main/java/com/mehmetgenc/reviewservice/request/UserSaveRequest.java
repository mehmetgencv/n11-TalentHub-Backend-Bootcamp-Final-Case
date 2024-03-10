package com.mehmetgenc.reviewservice.request;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserSaveRequest(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3 ,max = 100, message = "Name must be less than 3 or equal to 100 characters")
        String name,
        @NotBlank(message = "Surname is mandatory")
        @Size(min = 3 ,max = 100, message = "Surname must be less than 3 or equal to 100 characters")
        String surname,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        @Size(min = 5 ,max = 100, message = "Email must be less than 5 or equal to 100 characters")
        String email,
        @NotNull(message = "Gender is mandatory")
        Gender gender,
        @NotNull(message = "Latitude is mandatory")
        Double latitude,
        @NotNull(message = "Longitude is mandatory")
        Double longitude
) {}