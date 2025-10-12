package com.eugen.bookingservice.model;

import com.eugen.bookingservice.dto.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "outbox")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String payload;   // JSON
    private LocalDateTime createdAt;
}
