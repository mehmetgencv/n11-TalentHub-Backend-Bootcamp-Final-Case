package com.mehmetgenc.reviewservice.service.impl;


import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.dto.UserLocationDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.mapper.UserMapper;
import com.mehmetgenc.reviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final UserServiceImpl userServiceImpl;

    @Value("${recommendation.maxDistance}")
    double maxDistance;

    @Value("${recommendation.rate-weight}")
    double rateWeight;

    @Value("${recommendation.distance-weight}")
    double distanceWeight;

    @Override
    public List<RestaurantRecommendInfoDTO> getRecommendations(Long userId, List<RestaurantRecommendInfoDTO> restaurantInfoDTOList) {
        //get locations of user and restaurants then recommend restaurants
        User user = userServiceImpl.findById(userId);
        UserLocationDTO userLocationDTO = UserMapper.INSTANCE.convertToUserLocationDTO(user);
        double userLatitude = userLocationDTO.latitude();
        double userLongitude = userLocationDTO.longitude();
        List<RestaurantRecommendInfoDTO> recommendedRestaurants = new ArrayList<>();

        for (RestaurantRecommendInfoDTO restaurant : restaurantInfoDTOList) {
            double restaurantLatitude = restaurant.getLatitude();
            double restaurantLongitude = restaurant.getLongitude();


            double distance = calculateDistance(userLatitude, userLongitude, restaurantLatitude, restaurantLongitude);
            if (distance > maxDistance) {
                continue;
            }

            double distanceWeight = abs(1 - (distance / maxDistance));
            //if rate is null, set it to 0
            double rate = restaurant.getRate() == null ? 0.0 : restaurant.getRate();


            double totalScore = (rate * rateWeight) + (distanceWeight * distanceWeight);
            restaurant.setTotalScore(totalScore);
            recommendedRestaurants.add(restaurant);
        }


        Collections.sort(recommendedRestaurants, Comparator.comparingDouble(RestaurantRecommendInfoDTO::getTotalScore).reversed());


        List<RestaurantRecommendInfoDTO> topThreeRecommendations = recommendedRestaurants.subList(0, Math.min(recommendedRestaurants.size(), 3));

        return topThreeRecommendations;



    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in kilometers
        lat1 = abs(lat1);
        lon1 = abs(lon1);
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
