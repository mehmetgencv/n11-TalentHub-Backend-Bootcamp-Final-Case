package com.mehmetgenc.restaurantservice.controller.contract.impl;

import com.mehmetgenc.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.request.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.entity.Restaurant;
import com.mehmetgenc.restaurantservice.mapper.RestaurantMapper;
import com.mehmetgenc.restaurantservice.request.RestaurantUpdateRequest;
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
        Iterable<Restaurant> restaurantList = restaurantService.getAll();
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOList(restaurantList);
    }

    @Override
    public RestaurantDTO getById(String restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    @Override
    public void delete(String restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @Override
    public RestaurantDTO updateRate(String restaurantId, Double rate) {
        Restaurant restaurant = restaurantService.updateRate(restaurantId, rate);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    @Override
    public List<RestaurantDTO> saveBatch(List<RestaurantSaveRequest> restaurantSaveRequestList) {
        List<Restaurant> restaurantList = RestaurantMapper.INSTANCE.convertToRestaurantList(restaurantSaveRequestList);
        restaurantList = restaurantService.saveBatch(restaurantList);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOList(restaurantList);
    }

    @Override
    public RestaurantDTO update(String restaurantId, RestaurantUpdateRequest restaurantUpdateRequest) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(restaurantUpdateRequest);
        restaurant = restaurantService.update(restaurantId, restaurant);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);

    }
}
