package com.mehmetgenc.reviewservice.entity;

import com.mehmetgenc.reviewservice.entity.enums.Rate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    @Column(name = "RESTAURANT_ID", nullable = false)
    Long restaurantId;

    @Column(name = "COMMENT", length = 100)
    private String comment;

    @Column(name = "RATE", length = 10, nullable = false)
    private Rate rate;
}