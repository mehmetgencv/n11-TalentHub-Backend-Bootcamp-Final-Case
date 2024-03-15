package com.mehmetgenc.restaurantservice.service;

import com.mehmetgenc.restaurantservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    Iterable<Restaurant> getAll();

    Restaurant getById(String restaurantId);

    void delete(String restaurantId);

    Restaurant updateRate(String restaurantId, Double rate);

    List<Restaurant> saveBatch(List<Restaurant> restaurantList);

    Restaurant update(String restaurantId, Restaurant restaurant);
}
