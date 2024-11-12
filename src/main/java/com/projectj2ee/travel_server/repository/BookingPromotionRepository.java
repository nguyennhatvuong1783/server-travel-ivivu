package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.BookingPromotionIds;
import com.projectj2ee.travel_server.entity.BookingPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPromotionRepository extends JpaRepository<BookingPromotion, BookingPromotionIds> {
    List<BookingPromotion> findById_BookingId(int  bookingId);
    List<BookingPromotion> findById_PromotionId(int  promotionId);
}
