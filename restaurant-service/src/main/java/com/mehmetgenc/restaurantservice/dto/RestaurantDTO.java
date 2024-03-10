package com.mehmetgenc.restaurantservice.dto;

public record RestaurantDTO(
        String id,
        String name,
        Double rate,
        String address,
        String phone,
        String email,
        Double latitude,
        Double longitude
) {
}
