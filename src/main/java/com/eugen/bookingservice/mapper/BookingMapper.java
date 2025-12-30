package com.eugen.bookingservice.mapper;

import com.eugen.bookingservice.dto.BookingDTORequest;
import com.eugen.bookingservice.dto.BookingDTOResponse;
import com.eugen.bookingservice.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTOResponse toDto(Booking table);
    Booking toEntity(BookingDTORequest tableRequest);
}
