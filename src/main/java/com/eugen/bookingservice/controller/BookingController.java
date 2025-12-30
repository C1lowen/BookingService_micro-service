package com.eugen.bookingservice.controller;

import com.eugen.bookingservice.dto.BookingDTORequest;
import com.eugen.bookingservice.dto.BookingDTOResponse;
import com.eugen.bookingservice.sevice.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTORequest request) {
        BookingDTOResponse booking = bookingService.createBooking(request);
        return ResponseEntity.status(201).body(booking);
    }

    @PostMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllBooking() {

    }
}
