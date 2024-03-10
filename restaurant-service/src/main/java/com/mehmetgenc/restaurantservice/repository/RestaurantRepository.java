package com.mehmetgenc.restaurantservice.repository;

import com.mehmetgenc.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String>{
}
