package com.mehmetgenc.reviewservice.controller.contract;

import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;

public interface RestaurantControllerContract {
    RestResponse<RestaurantInfoDTO> findById(Long restaurantId);

    RestResponse<RestaurantInfoDTO> updateRate(Long restaurantId, Double rate);
}
