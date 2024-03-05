package com.mehmetgenc.restaurantservice.exception;

public class RestaurantNotFoundException extends RestaurantServiceException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
