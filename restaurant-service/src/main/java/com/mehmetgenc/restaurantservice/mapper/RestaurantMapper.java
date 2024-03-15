package com.mehmetgenc.restaurantservice.mapper;


import com.mehmetgenc.restaurantservice.dto.RestaurantDTO;
import com.mehmetgenc.restaurantservice.request.RestaurantSaveRequest;
import com.mehmetgenc.restaurantservice.entity.Restaurant;
import com.mehmetgenc.restaurantservice.request.RestaurantUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant convertToRestaurant(RestaurantSaveRequest restaurantSaveRequest);

    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);

    List<RestaurantDTO> convertToRestaurantDTOList(Iterable<Restaurant> restaurantList);

    List<Restaurant> convertToRestaurantList(List<RestaurantSaveRequest> restaurantSaveRequestList);

    Restaurant convertToRestaurant(RestaurantUpdateRequest restaurantUpdateRequest);
}
