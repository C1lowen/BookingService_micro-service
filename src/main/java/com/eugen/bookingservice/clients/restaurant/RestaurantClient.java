package com.eugen.bookingservice.clients.restaurant;

import com.eugen.bookingservice.clients.model.RestaurantTableResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "restaurant-service",
        path = "/api/v1/tables"
)
public interface RestaurantClient {

    @GetMapping("/{id}")
    ResponseEntity<RestaurantTableResponse> findById(@PathVariable Long id);

    @PostMapping("/reserve/{id}")
    ResponseEntity<RestaurantTableResponse> reservedTableById(@PathVariable Long id);
}
