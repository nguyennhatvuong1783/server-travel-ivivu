package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.BookingPromotionIds;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "booking_promotions")
public class BookingPromotion {
    @EmbeddedId
    BookingPromotionIds id;
}
