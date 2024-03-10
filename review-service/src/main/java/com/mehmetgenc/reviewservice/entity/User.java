package com.mehmetgenc.reviewservice.entity;

import com.mehmetgenc.reviewservice.entity.enums.Gender;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name length should be between 3 and 100")
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 100, message = "Surname length should be between 3 and 100")
    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @NotNull(message = "Gender is mandatory")
    @Column(name = "GENDER", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 100, message = "Email length should be between 3 and 100")
    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @NotNull(message = "Latitude is mandatory")
    @Column(name = "LATITUDE", length = 100, nullable = false)
    private Double latitude;

    @NotNull(message = "Longitude is mandatory")
    @Column(name = "LONGITUDE", length = 100, nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}