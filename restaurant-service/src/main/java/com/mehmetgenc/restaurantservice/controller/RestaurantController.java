package com.mehmetgenc.restaurantservice.controller;

import com.mehmetgenc.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.request.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.general.KafkaProducerService;
import com.mehmetgenc.restaurantservice.general.RestResponse;
import com.mehmetgenc.restaurantservice.request.RestaurantUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Validated
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
@CrossOrigin("http://localhost:3000")
public class RestaurantController {
    private final RestaurantControllerContract restaurantControllerContract;
    private final KafkaProducerService kafkaProducerService;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract, KafkaProducerService kafkaProducerService) {
        this.restaurantControllerContract = restaurantControllerContract;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant", description = "Creates a new restaurant with the provided details")
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody @Valid RestaurantSaveRequest restaurantSaveRequest){
        RestaurantDTO restaurantDto = restaurantControllerContract.save(restaurantSaveRequest);
        kafkaProducerService.sendMessage("infoLog", "Restaurant created with id: " + restaurantDto.id());
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Retrieves all active restaurants")
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getAllRestaurants(){
        Iterable<RestaurantDTO> restaurantDtoList = restaurantControllerContract.getAll();
        return ResponseEntity.ok(RestResponse.of(restaurantDtoList));
    }

    @PutMapping("/{restaurantId}")
    @Operation(summary = "Update Restaurant by ID", description = "Updates a single restaurant based on the provided ID")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@PathVariable String restaurantId, @RequestBody @Valid RestaurantUpdateRequest restaurantUpdateRequest){
        RestaurantDTO restaurantDto = restaurantControllerContract.update(restaurantId, restaurantUpdateRequest);
        kafkaProducerService.sendMessage("infoLog", "Restaurant updated with id: " + restaurantDto.id());
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Get Restaurant by ID", description = "Returns a single restaurant based on the provided ID")
    public ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable String restaurantId){
        RestaurantDTO restaurantDto = restaurantControllerContract.getById(restaurantId);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @DeleteMapping("/{restaurantId}")
    @Operation(summary = "Delete Restaurant by ID", description = "Deletes a single restaurant based on the provided ID")
    public ResponseEntity<RestResponse<String>> deleteRestaurantById(@PathVariable String restaurantId){
        restaurantControllerContract.delete(restaurantId);
        kafkaProducerService.sendMessage("infoLog", "Restaurant deleted with id: " + restaurantId);
        return ResponseEntity.ok(RestResponse.of("Restaurant deleted"));
    }

    @PostMapping("/updateRate/{restaurantId}")
    @Operation(summary = "Update rate of the restaurant By Restaurant ID", description = "Updates a single restaurant rate based on the provided ID")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRateRestaurant(@PathVariable String restaurantId, @RequestParam Double rate){
        RestaurantDTO restaurantDto = restaurantControllerContract.updateRate(restaurantId, rate);
        kafkaProducerService.sendMessage("infoLog", "Restaurant rate updated with id: " + restaurantId);
        return ResponseEntity.ok(RestResponse.of(restaurantDto));
    }

    @PostMapping("/saveBatchRestaurants")
    @Operation(summary = "Create new restaurants", description = "Creates new restaurants with the provided details")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> saveBatchRestaurants(@RequestBody List<RestaurantSaveRequest> restaurantSaveRequestList){
        List<RestaurantDTO> restaurantDtoList = restaurantControllerContract.saveBatch(restaurantSaveRequestList);
        return ResponseEntity.ok(RestResponse.of(restaurantDtoList));
    }

}
