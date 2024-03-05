package com.mehmetgenc.restaurantservice.controller.contract.impl;

import com.mehmetgenc.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.entity.Restaurant;
import com.mehmetgenc.restaurantservice.mapper.RestaurantMapper;
import com.mehmetgenc.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantService restaurantService;
    @Override
    public RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(restaurantSaveRequest);
        restaurant = restaurantService.save(restaurant);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);

    }

    @Override
    public List<RestaurantDTO> getAll() {
        List<Restaurant> restaurantList = restaurantService.getAll();
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOList(restaurantList);
    }

    @Override
    public RestaurantDTO getById(Long restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    @Override
    public void delete(Long restaurantId) {
        restaurantService.delete(restaurantId);
    }
}
