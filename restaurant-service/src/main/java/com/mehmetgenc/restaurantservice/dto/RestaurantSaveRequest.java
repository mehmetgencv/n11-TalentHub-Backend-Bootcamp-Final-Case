package com.mehmetgenc.restaurantservice.dto;

public record RestaurantSaveRequest(
        String id,
        String name,
        String address,

        Double latitude,
        Double longitude,
        String phone,
        String email,
        String description
) {
}
