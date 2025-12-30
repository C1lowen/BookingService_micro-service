package com.eugen.bookingservice.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookingTimeValidator.class)
@Documented
public @interface ValidBookingTime {

    String message() default "Invalid booking time range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
