package com.mehmetgenc.reviewservice.service;

import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;

import java.util.List;

public interface RecommendationService {
    List<RestaurantRecommendInfoDTO> getRecommendations(Long userId, List<RestaurantRecommendInfoDTO> restaurantRecommendInfoDTOS);
}
