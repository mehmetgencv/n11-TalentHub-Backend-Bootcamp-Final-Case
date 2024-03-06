package com.mehmetgenc.restaurantservice.dto;

public record RestaurantDTO(
        Long id,
        String name,
        Double rate,
        String address,
        String phone,
        String email
) {
}
