package com.mehmetgenc.reviewservice.service.impl;

import com.mehmetgenc.reviewservice.dto.RestaurantRecommendInfoDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.entity.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    private RecommendationServiceImpl recommendationServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        recommendationServiceImplUnderTest = new RecommendationServiceImpl(mockUserServiceImpl);
        recommendationServiceImplUnderTest.maxDistance = 0.0;
        recommendationServiceImplUnderTest.rateWeight = 0.0;
        recommendationServiceImplUnderTest.distanceWeight = 0.0;
    }

    @Test
    void testGetRecommendations() {
        // given
        final Long userId = 0L;
        final List<RestaurantRecommendInfoDTO> restaurantInfoDTOList = List.of(createRestaurantRecommendInfoDTO());
        final User user = createUser();
        when(mockUserServiceImpl.findById(userId)).thenReturn(user);

        // when
        final List<RestaurantRecommendInfoDTO> result = recommendationServiceImplUnderTest.getRecommendations(userId, restaurantInfoDTOList);

        // then

        assertEquals(1, result.size());
    }

    @Test
    void testGetRecommendations_WhenDistanceIsGreaterThanMaxDistance() {
        // given
        final Long userId = 0L;
        final List<RestaurantRecommendInfoDTO> restaurantInfoDTOList = List.of(createRestaurantRecommendInfoDTO());
        final User user = createUser();
        when(mockUserServiceImpl.findById(userId)).thenReturn(user);
        recommendationServiceImplUnderTest.maxDistance = -1;

        // when
        final List<RestaurantRecommendInfoDTO> result = recommendationServiceImplUnderTest.getRecommendations(userId, restaurantInfoDTOList);

        // then
        assertEquals(0, result.size());
    }

    @Test
    void testGetRecommendations_RestaurantNotInList() {
        // given
        final Long userId = 0L;
        final List<RestaurantRecommendInfoDTO> restaurantInfoDTOList = List.of();
        final User user = createUser();
        when(mockUserServiceImpl.findById(userId)).thenReturn(user);

        // when
        final List<RestaurantRecommendInfoDTO> result = recommendationServiceImplUnderTest.getRecommendations(userId, restaurantInfoDTOList);

        // then
        assertEquals(0, result.size());
    }



    private RestaurantRecommendInfoDTO createRestaurantRecommendInfoDTO() {
        RestaurantRecommendInfoDTO restaurantRecommendInfoDTO = new RestaurantRecommendInfoDTO();
        restaurantRecommendInfoDTO.setTotalScore(5.0);
        restaurantRecommendInfoDTO.setRate(5.0);
        restaurantRecommendInfoDTO.setLatitude(5.0);
        restaurantRecommendInfoDTO.setLongitude(5.0);
        restaurantRecommendInfoDTO.setId(1L);
        restaurantRecommendInfoDTO.setName("name");
        return restaurantRecommendInfoDTO;
    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setLatitude(5.0);
        user.setLongitude(5.0);
        user.setEmail("email@email");
        user.setGender(Gender.MALE);
        user.setName("name");
        return user;
    }
}
