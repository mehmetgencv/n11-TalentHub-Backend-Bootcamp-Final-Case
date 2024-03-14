package com.mehmetgenc.reviewservice.client;

import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "restaurantService", url = "${restaurant.service.url}")
public interface RestaurantServiceClient {

    @GetMapping("/{userId}")
    RestResponse<RestaurantInfoDTO> findById(@PathVariable Long userId);

    @PostMapping(value = "/updateRate/{restaurantId}")
    RestResponse<RestaurantInfoDTO> updateRate(@PathVariable Long restaurantId, @RequestParam Double rate);

    @GetMapping
    RestResponse<List<RestaurantRecommendInfoDTO>> getAllRestaurants();
}
