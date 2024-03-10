package com.mehmetgenc.restaurantservice.controller.contract;

import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;

import java.util.List;

public interface RestaurantControllerContract {
    RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest);

    List<RestaurantDTO> getAll();

    RestaurantDTO getById(String restaurantId);

    void delete(String restaurantId);

    RestaurantDTO updateRate(String restaurantId, Double rate);

    List<RestaurantDTO> saveBatch(List<RestaurantSaveRequest> restaurantSaveRequestList);
}
