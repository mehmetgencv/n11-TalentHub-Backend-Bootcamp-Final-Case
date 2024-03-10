package com.mehmetgenc.restaurantservice.controller;

import com.mehmetgenc.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.general.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Validated
public class RestaurantController {
    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody @Valid RestaurantSaveRequest restaurantSaveRequest){
        RestaurantDTO restaurantDto = restaurantControllerContract.save(restaurantSaveRequest);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getAllRestaurants(){
        Iterable<RestaurantDTO> restaurantDtoList = restaurantControllerContract.getAll();
        return ResponseEntity.ok(RestResponse.of(restaurantDtoList));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable String restaurantId){
        RestaurantDTO restaurantDto = restaurantControllerContract.getById(restaurantId);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<RestResponse<String>> deleteRestaurantById(@PathVariable String restaurantId){
        restaurantControllerContract.delete(restaurantId);
        return ResponseEntity.ok(RestResponse.of("Restaurant deleted"));
    }

    @PostMapping("/updateRate/{restaurantId}")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRateRestaurant(@PathVariable String restaurantId, @RequestParam Double rate){
        RestaurantDTO restaurantDto = restaurantControllerContract.updateRate(restaurantId, rate);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @PostMapping("/saveBatchRestaurants")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> saveBatchRestaurants(@RequestBody List<RestaurantSaveRequest> restaurantSaveRequestList){
        List<RestaurantDTO> restaurantDtoList = restaurantControllerContract.saveBatch(restaurantSaveRequestList);
        return ResponseEntity.ok(RestResponse.of(restaurantDtoList));
    }

}
