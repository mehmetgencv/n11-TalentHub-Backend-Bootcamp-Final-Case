package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.RecommendationControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recommendations")
@RequiredArgsConstructor
@Tag(name = "Recommendation Controller", description = "Recommendation Management")
public class RecommendationController {
    private final RecommendationControllerContract recommendationControllerContract;

    @GetMapping("/recommendations/{userId}")
    @Operation(summary = "Get Recommendations by User ID", description = "Returns a list of recommended restaurants based on the provided user ID")
    public ResponseEntity<RestResponse<List<RestaurantRecommendInfoDTO>>> getRecommendations(@PathVariable Long userId) {
        List<RestaurantRecommendInfoDTO> recommendations = recommendationControllerContract.getRecommendations(userId);
        return ResponseEntity.ok(RestResponse.of(recommendations));

    }

}
