package com.mehmetgenc.reviewservice.entity;

import com.mehmetgenc.reviewservice.entity.enums.Rate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank(message = "Restaurant Id is mandatory")
    @Column(name = "RESTAURANT_ID", nullable = false)
    private Long restaurantId;

    @Column(name = "COMMENT", length = 100)
    private String comment;
    @NotNull(message = "Rate is mandatory")
    @Column(name = "RATE", length = 10, nullable = false)
    private Rate rate;
}