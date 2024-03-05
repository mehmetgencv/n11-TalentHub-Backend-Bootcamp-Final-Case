package com.mehmetgenc.restaurantservice.service;

import com.mehmetgenc.restaurantservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAll();
}
