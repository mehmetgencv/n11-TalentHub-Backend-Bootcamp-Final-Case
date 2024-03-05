package com.mehmetgenc.restaurantservice.controller;

import com.mehmetgenc.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.general.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantSaveRequest restaurantSaveRequest){
        RestaurantDTO restaurantDto = restaurantControllerContract.save(restaurantSaveRequest);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants(){
        List<RestaurantDTO> restaurantDtoList = restaurantControllerContract.getAll();
        return ResponseEntity.ok(RestResponse.of(restaurantDtoList));
    }
}
