package com.eugen.bookingservice.sevice;

import com.eugen.bookingservice.clients.model.RestaurantTableResponse;
import com.eugen.bookingservice.clients.restaurant.RestaurantClient;
import com.eugen.bookingservice.dto.BookingDTORequest;
import com.eugen.bookingservice.dto.BookingDTOResponse;
import com.eugen.bookingservice.dto.enums.BookingStatus;
import com.eugen.bookingservice.exception.BookingException;
import com.eugen.bookingservice.mapper.BookingMapper;
import com.eugen.bookingservice.model.Booking;
import com.eugen.bookingservice.repository.BookingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RestaurantClient restaurantClient;

    @Transactional
    @CircuitBreaker(
            name = "restaurantCB",
            fallbackMethod = "findByIdFallback"
    )
    public BookingDTOResponse createBooking(BookingDTORequest bookingDTORequest) {
        Booking entity = bookingMapper.toEntity(bookingDTORequest);
        entity.setStatus(BookingStatus.CONFIRMED);
        entity.setBookingTime(LocalDateTime.now());
        entity.setUserId("FAKE_ID");

        ResponseEntity<RestaurantTableResponse> restaurantTableResponseEntity = restaurantClient.reservedTableById(bookingDTORequest.getTableId());

        if(bookingRepository.existsOverlappingBooking(
                bookingDTORequest.getTableId(),
                bookingDTORequest.getStartTime(),
                bookingDTORequest.getEndTime())) {
            throw new BookingException(String.format(
                    "Table number %d is not available at this time",
                    bookingDTORequest.getTableId()
            ));
        }

        Booking bookingSaved = bookingRepository.save(entity);

        return bookingMapper.toDto(bookingSaved);
    }

    public BookingDTOResponse findByIdFallback(
            BookingDTORequest bookingDTORequest,
            Throwable ex
    ) {
            log.warn("Microservice error: " + ex.getMessage());
            throw new BookingException(String.format(
                    "Table number %d cannot be booked", bookingDTORequest.getTableId()
            ));
    }
}
