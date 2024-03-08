package com.mehmetgenc.restaurantservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Double rate;
    private double latitude;
    private double longitude;

    public Restaurant(String name, String address, String phone, String email, Double rate, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.rate = rate;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
