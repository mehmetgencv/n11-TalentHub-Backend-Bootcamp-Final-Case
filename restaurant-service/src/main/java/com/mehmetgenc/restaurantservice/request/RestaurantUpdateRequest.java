package com.mehmetgenc.restaurantservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record RestaurantUpdateRequest(

        @NotBlank(message = "Name is mandatory")
        String name,

        String address,

        @NotNull(message = "Latitude is mandatory")
        Double latitude,

        @NotNull(message = "Longitude is mandatory")
        Double longitude,


        @NotBlank(message = "Phone is mandatory")
        String phone,

        @NotBlank(message = "Email is mandatory")
        String email
) {}
