package com.mehmetgenc.restaurantservice.entity;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@SolrDocument(solrCoreName = "restaurant-service")
public class Restaurant {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @NotBlank(message = "Name is mandatory")
    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "address", type = "string")
    private String address;


    @NotBlank(message = "Phone is mandatory")
    @Indexed(name = "phone", type = "string")
    private String phone;

    @NotBlank(message = "Email is mandatory")
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

    public Restaurant(String name, String address, String phone, String email, double latitude, double longitude) {
        this.id = String.valueOf(ID_GENERATOR.incrementAndGet());
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Restaurant(String name, String phone, String email, double latitude, double longitude) {
        this.id = String.valueOf(ID_GENERATOR.incrementAndGet());
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Restaurant() {
        this.id = String.valueOf(ID_GENERATOR.incrementAndGet());
    }
}
