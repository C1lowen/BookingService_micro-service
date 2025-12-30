package com.eugen.bookingservice.dto.validator;

import com.eugen.bookingservice.dto.BookingDTORequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;
import java.time.LocalDateTime;

public class BookingTimeValidator
        implements ConstraintValidator<ValidBookingTime, BookingDTORequest> {

    private static final long MIN_DURATION_MINUTES = 30;

    @Override
    public boolean isValid(BookingDTORequest request,
                           ConstraintValidatorContext context) {

        if (request.getStartTime() == null || request.getEndTime() == null) {
            return true; // @NotNull отработает отдельно
        }

        LocalDateTime start = request.getStartTime();
        LocalDateTime end = request.getEndTime();

        // 1. start < end
        if (!start.isBefore(end)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Start time must be before end time"
            ).addConstraintViolation();
            return false;
        }

        // 2. минимум 30 минут
        long minutes = Duration.between(start, end).toMinutes();
        if (minutes < MIN_DURATION_MINUTES) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Booking duration must be at least 30 minutes"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
