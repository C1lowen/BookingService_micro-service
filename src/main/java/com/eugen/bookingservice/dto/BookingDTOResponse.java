package com.eugen.bookingservice.dto;

import com.eugen.bookingservice.dto.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTOResponse {
    private Long id;
    private String userId;
    private Long tableId;
    private BookingStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime bookingTime;
    private LocalDateTime updatedAt;
}
