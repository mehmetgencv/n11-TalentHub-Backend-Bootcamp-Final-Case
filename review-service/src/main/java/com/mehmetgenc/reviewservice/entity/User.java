package com.mehmetgenc.reviewservice.entity;

import com.mehmetgenc.reviewservice.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "GENDER", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "LATITUDE", length = 100, nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", length = 100, nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}