package com.mehmetgenc.restaurantservice.service.impl;

import com.mehmetgenc.restaurantservice.entity.Restaurant;
import com.mehmetgenc.restaurantservice.repository.RestaurantRepository;
import com.mehmetgenc.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
