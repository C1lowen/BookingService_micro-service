package com.eugen.bookingservice.repository;

import com.eugen.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
        FROM Booking b
        WHERE b.tableId = :tableId
          AND b.status = 'CONFIRMED'
          AND NOT (
              b.endTime <= :requestedStart
              OR b.startTime >= :requestedEnd
          )
    """)
    boolean existsOverlappingBooking(
            @Param("tableId") Long tableId,
            @Param("requestedStart") LocalDateTime requestedStart,
            @Param("requestedEnd") LocalDateTime requestedEnd
    );
}
