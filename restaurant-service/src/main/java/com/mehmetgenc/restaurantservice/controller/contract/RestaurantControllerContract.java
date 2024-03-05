package com.mehmetgenc.restaurantservice.controller.contract;

import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;

import java.util.List;

public interface RestaurantControllerContract {
    RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest);

    List<RestaurantDTO> getAll();
}
