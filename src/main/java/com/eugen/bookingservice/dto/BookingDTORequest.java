package com.eugen.bookingservice.dto;

import com.eugen.bookingservice.dto.validator.ValidBookingTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@ValidBookingTime
@Data
public class BookingDTORequest {
    @NotNull
    private Long tableId;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
