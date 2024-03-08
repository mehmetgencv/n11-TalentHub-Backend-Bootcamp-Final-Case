package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.client.RestaurantServiceClient;
import com.mehmetgenc.reviewservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;

    @GetMapping("/getRestaurant")
    public RestResponse<RestaurantInfoDTO> findById(Long restaurantId) {
        return restaurantControllerContract.findById(restaurantId);
    }

    // updateRate method
    @PatchMapping("/updateRate")
    public RestResponse<RestaurantInfoDTO> updateRate(Long restaurantId, Double rate) {
        return restaurantControllerContract.updateRate(restaurantId, rate);
    }

    // get ALL restaurants
    @GetMapping
    public RestResponse<List<RestaurantRecommendInfoDTO>> getAllRestaurants() {
        return restaurantControllerContract.getAllRestaurants();
    }

}
