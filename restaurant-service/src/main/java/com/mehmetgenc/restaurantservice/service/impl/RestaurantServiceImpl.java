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
    public Iterable<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(String restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant not found"));
    }

    @Override
    public void delete(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant updateRate(String restaurantId, Double rate) {
        Restaurant restaurant = getById(restaurantId);
        Double currentRate = restaurant.getRate();
        if(currentRate != null){
            rate = (currentRate + rate) / 2;
        }
        restaurant.setRate(rate);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> saveBatch(List<Restaurant> restaurantList) {
        return (List<Restaurant>) restaurantRepository.saveAll(restaurantList);
    }

    @Override
    public Restaurant update(String restaurantId, Restaurant restaurant) {
        Restaurant restaurantFromDb = getById(restaurantId);
        restaurantFromDb.setName(restaurant.getName());
        restaurantFromDb.setAddress(restaurant.getAddress());
        restaurantFromDb.setEmail(restaurant.getEmail());
        restaurantFromDb.setPhone(restaurant.getPhone());
        restaurantFromDb.setLatitude(restaurant.getLatitude());
        restaurantFromDb.setLongitude(restaurant.getLongitude());
        return restaurantRepository.save(restaurantFromDb);
    }


}
