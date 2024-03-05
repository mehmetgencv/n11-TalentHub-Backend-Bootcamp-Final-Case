package com.mehmetgenc.restaurantservice.service.impl;

import com.mehmetgenc.restaurantservice.entity.Restaurant;
import com.mehmetgenc.restaurantservice.exception.RestaurantNotFoundException;
import com.mehmetgenc.restaurantservice.repository.RestaurantRepository;
import com.mehmetgenc.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant not found"));
    }

    @Override
    public void delete(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }


}
