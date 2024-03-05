package com.mehmetgenc.restaurantservice.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String address,
        String phone,
        String email
) {
}
