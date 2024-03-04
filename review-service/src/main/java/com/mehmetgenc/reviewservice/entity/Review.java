package com.mehmetgenc.reviewservice.entity;

import com.mehmetgenc.reviewservice.entity.enums.Rate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    private Restaurant restaurant;

    @Column(name = "COMMENT", length = 100)
    private String comment;

    @Column(name = "RATE", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Rate rate;
}