package com.mehmetgenc.restaurantservice.dto;

public record RestaurantSaveRequest(
        String name,
        String address,
        Double longitude,
        Double latitude,
        String phone,
        String email,
        String description
) {
}
