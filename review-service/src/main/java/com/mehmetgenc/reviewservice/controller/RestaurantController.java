package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.client.RestaurantServiceClient;
import com.mehmetgenc.reviewservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.reviewservice.dto.RestaurantInfoDTO;
import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.general.KafkaProducerService;
import com.mehmetgenc.reviewservice.general.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant Controller", description = "Restaurant Management with Feign Client")
@CrossOrigin("${cross.origin.url}")
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/getRestaurant")
    @Operation(summary = "Get Restaurant by ID", description = "Returns a single restaurant based on the provided ID")
    public RestResponse<RestaurantInfoDTO> findById(Long restaurantId) {
        return restaurantControllerContract.findById(restaurantId);
    }


    @PatchMapping("/updateRate")
    @Operation(summary = "Update rate of the restaurant By Restaurant ID", description = "Updates a single restaurant rate based on the provided ID")
    public RestResponse<RestaurantInfoDTO> updateRate(Long restaurantId, Double rate) {
        RestResponse<RestaurantInfoDTO> restaurantInfoDTORestResponse = restaurantControllerContract.updateRate(restaurantId, rate);
        kafkaProducerService.sendMessage("infoLog", "Restaurant rate updated with id: " + restaurantId);
        return restaurantInfoDTORestResponse;
    }

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Retrieves all active restaurants")
    public RestResponse<List<RestaurantRecommendInfoDTO>> getAllRestaurants() {
        return restaurantControllerContract.getAllRestaurants();
    }

}
