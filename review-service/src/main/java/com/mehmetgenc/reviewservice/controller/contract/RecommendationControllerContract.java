package com.mehmetgenc.reviewservice.controller.contract;

import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;

import java.util.List;

public interface RecommendationControllerContract {
    List<RestaurantRecommendInfoDTO> getRecommendations(Long userId);
}
