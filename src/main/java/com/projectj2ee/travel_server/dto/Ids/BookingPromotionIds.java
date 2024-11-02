package com.projectj2ee.travel_server.dto.Ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingPromotionIds implements Serializable {
    @Column(name = "booking_id")
    int bookingId;

    @Column(name = "promotion_id")
    int promotionId;
}
