package com.mehmetgenc.reviewservice.client;

import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "restaurantService", url = "http://localhost:8081/api/v1/restaurants")
public interface RestaurantServiceClient {

    @GetMapping("/{userId}")
    RestResponse<RestaurantInfoDTO> findById(@PathVariable Long userId);

    @PostMapping(value = "/updateRate/{restaurantId}")
    RestResponse<RestaurantInfoDTO> updateRate(@PathVariable Long restaurantId, @RequestParam Double rate);
}
