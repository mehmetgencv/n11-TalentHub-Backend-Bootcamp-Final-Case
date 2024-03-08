package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.RecommendationControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationControllerContract recommendationControllerContract;

    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<RestResponse<List<RestaurantRecommendInfoDTO>>> getRecommendations(@PathVariable Long userId) {
        List<RestaurantRecommendInfoDTO> recommendations = recommendationControllerContract.getRecommendations(userId);
        return ResponseEntity.ok(RestResponse.of(recommendations));

    }

}
