package com.mehmetgenc.restaurantservice.service;

import com.mehmetgenc.restaurantservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAll();

    Restaurant getById(Long restaurantId);

    void delete(Long restaurantId);

    Restaurant updateRate(Long restaurantId, Double rate);

    List<Restaurant> saveBatch(List<Restaurant> restaurantList);
}
