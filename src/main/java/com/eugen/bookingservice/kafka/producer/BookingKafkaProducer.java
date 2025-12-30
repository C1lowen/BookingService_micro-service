package com.eugen.bookingservice.kafka.producer;

import com.eugen.bookingservice.kafka.model.BookingKafkaEvent;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

//@Service
//@AllArgsConstructor
//public class BookingKafkaProducer {
//
//    private static final Logger log = LoggerFactory.getLogger(BookingKafkaProducer.class);
//
//    private final KafkaTemplate<String, BookingKafkaEvent> kafkaTemplate;
//
//    private BookingKafkaEvent convertToKafkaEvent(BookingEvent entity) {
//        return BookingKafkaEvent.builder()
//                .bookingId(entity.getBookingId())
//                .eventType(entity.getEventType())
//                .payload(entity.getPayload())
//                .createdAt(entity.getCreatedAt())
//                .build();
//    }
//
//    public void sendBookingToKafka(BookingEvent bookingEvent) {
//        BookingKafkaEvent kafkaEvent = convertToKafkaEvent(bookingEvent);
//        String key = String.valueOf(bookingEvent.getBookingId());
//
//        kafkaTemplate.send("booking", key, kafkaEvent);
//        log.info("Order sent to kafka: id={}", bookingEvent.getBookingId());
//    }
//}
