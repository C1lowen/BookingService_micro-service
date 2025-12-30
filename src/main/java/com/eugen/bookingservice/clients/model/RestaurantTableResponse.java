package com.eugen.bookingservice.clients.model;

import lombok.Data;

@Data
public class RestaurantTableResponse {
    private Long id;
    private int number;
    private int capacity;
    private TableStatus status;
    private LocationType location;
}
