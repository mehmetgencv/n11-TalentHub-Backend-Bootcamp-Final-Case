package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.mehmetgenc.reviewservice.client.RestaurantServiceClient;
import com.mehmetgenc.reviewservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantServiceClient restaurantServiceClient;
    @Override
    public RestResponse<RestaurantInfoDTO> findById(Long restaurantId) {
        return restaurantServiceClient.findById(restaurantId);
    }

    @Override
    public RestResponse<RestaurantInfoDTO> updateRate(Long restaurantId, Double rate) {
        return restaurantServiceClient.updateRate(restaurantId, rate);
    }

    @Override
    public RestResponse<List<RestaurantRecommendInfoDTO>> getAllRestaurants() {
        return restaurantServiceClient.getAllRestaurants();
    }
}
