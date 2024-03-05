package com.mehmetgenc.restaurantservice.repository;

import com.mehmetgenc.restaurantservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
