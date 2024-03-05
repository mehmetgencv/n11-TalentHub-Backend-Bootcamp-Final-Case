package com.mehmetgenc.restaurantservice.controller.contract;

import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;

public interface RestaurantControllerContract {
    RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest);
}
