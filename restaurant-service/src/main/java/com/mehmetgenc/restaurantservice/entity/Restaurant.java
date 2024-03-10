package com.mehmetgenc.restaurantservice.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@RequiredArgsConstructor
@SolrDocument(solrCoreName = "restaurant-service")
public class Restaurant {
    @Id
    @Indexed(name = "id", type = "string")
    private String  id;

    @NotBlank(message = "Name is mandatory")
    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "address", type = "string")
    private String address;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone is mandatory")
    @Indexed(name = "phone", type = "string")
    private String phone;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Indexed(name = "email", type = "string")
    private String email;

    @Indexed(name = "rate", type = "pdouble")
    private Double rate;

    @NotBlank(message = "Latitude is mandatory")
    @Indexed(name = "latitude", type = "pdouble")
    private double latitude;

    @NotBlank(message = "Longitude is mandatory")
    @Indexed(name = "longitude", type = "pdouble")
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
