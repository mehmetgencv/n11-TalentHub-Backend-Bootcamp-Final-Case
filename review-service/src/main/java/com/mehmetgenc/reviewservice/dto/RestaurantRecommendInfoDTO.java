package com.mehmetgenc.reviewservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class RestaurantRecommendInfoDTO
{
    private Long id;
    private String name;
    private Double rate;
    private Double latitude;
    private Double longitude;
    private Double totalScore;


}
