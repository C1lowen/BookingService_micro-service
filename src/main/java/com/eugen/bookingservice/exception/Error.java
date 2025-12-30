package com.eugen.bookingservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Error {
    private final String errorMessage;
    private final LocalDate date;
}
