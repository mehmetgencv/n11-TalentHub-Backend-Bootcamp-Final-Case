package com.mehmetgenc.reviewservice.controller.contract.impl;

import com.mehmetgenc.reviewservice.controller.contract.RecommendationControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationControllerContractImpl implements RecommendationControllerContract {

    private final RestaurantControllerContractImpl restaurantControllerContractImpl;
    private final RecommendationService recommendationService;

    @Override
    public List<RestaurantRecommendInfoDTO> getRecommendations(Long userId) {

        List<RestaurantRecommendInfoDTO> restaurantRecommendInfoDTOS = restaurantControllerContractImpl.getAllRestaurants().getData();
        return recommendationService.getRecommendations(userId, restaurantRecommendInfoDTOS);


    }
}
