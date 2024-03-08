package com.mehmetgenc.restaurantservice.mapper;


import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.dto.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);


    Restaurant convertToRestaurant(RestaurantSaveRequest restaurantSaveRequest);

    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);

    List<RestaurantDTO> convertToRestaurantDTOList(List<Restaurant> restaurantList);

    List<Restaurant> convertToRestaurantList(List<RestaurantSaveRequest> restaurantSaveRequestList);
}
