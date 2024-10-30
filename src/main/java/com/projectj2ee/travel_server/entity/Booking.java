package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    int id;

    @Column(name = "user_id",nullable = false)
    int userId;

    @Column(name = "tour_date_id",nullable = false)
    int tourDateId;

    @Column(name = "number_of_participants")
    int participants;

    @Column(name = "total_price",nullable = false,precision = 10,scale = 2)
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusBooking status;


}
