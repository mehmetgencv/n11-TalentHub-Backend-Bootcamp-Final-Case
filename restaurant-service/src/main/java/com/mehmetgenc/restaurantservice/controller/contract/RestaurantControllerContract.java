package com.mehmetgenc.restaurantservice.controller.contract;

import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;

import java.util.List;

public interface RestaurantControllerContract {
    RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest);

    List<RestaurantDTO> getAll();

    RestaurantDTO getById(Long restaurantId);

    void delete(Long restaurantId);

    RestaurantDTO updateRate(Long restaurantId, Double rate);
}
