package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.BookingPromotionIds;
import com.projectj2ee.travel_server.dto.request.BookingPromotionRequest;
import com.projectj2ee.travel_server.entity.BookingPromotion;
import com.projectj2ee.travel_server.repository.BookingPromotionRepository;
import com.projectj2ee.travel_server.repository.BookingRepository;
import com.projectj2ee.travel_server.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingPromotionService {
    @Autowired
    private final BookingPromotionRepository bookingPromotionRepository;

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final PromotionRepository promotionRepository;

    public String addPromotionToBooking(BookingPromotionRequest bookingPromotionRequest){
        if (!bookingRepository.existsById(bookingPromotionRequest.getBookingId())){
            throw new RuntimeException("Booking not found");
        }

        if (!promotionRepository.existsById(bookingPromotionRequest.getPromotionId())){
            throw new RuntimeException("Promotion not found");
        }
        BookingPromotion entity = new BookingPromotion();
        BookingPromotionIds id = new BookingPromotionIds();
        id.setBookingId(bookingPromotionRequest.getBookingId());
        id.setPromotionId(bookingPromotionRequest.getPromotionId());
        entity.setId(id);
        bookingPromotionRepository.save(entity);
        return "Success";
    }

    public String deletePromotionBooking(BookingPromotionRequest bookingPromotionRequest){
        BookingPromotion entity = new BookingPromotion();
        BookingPromotionIds id = new BookingPromotionIds();
        id.setBookingId(bookingPromotionRequest.getBookingId());
        id.setPromotionId(bookingPromotionRequest.getPromotionId());
        entity.setId(id);
        bookingPromotionRepository.delete(entity);
        return "Success";
    }

}
