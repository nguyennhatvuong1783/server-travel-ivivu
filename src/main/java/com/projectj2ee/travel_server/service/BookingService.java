package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import com.projectj2ee.travel_server.dto.request.BookingRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Booking;
import com.projectj2ee.travel_server.mapper.BookingMapper;
import com.projectj2ee.travel_server.repository.BookingRepository;
import com.projectj2ee.travel_server.repository.TourDateRepository;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TourDateRepository tourDateRepository;

    @Autowired
    private BookingMapper bookingMapper;

    public ApiResponse<List<Booking>> getAllBooking(){
        return new ApiResponse<List<Booking>>(HttpStatus.OK.value(), "Success",bookingRepository.findAll());
    }

    public ApiResponse<Booking> getBookingById(String id){
        Booking entity = bookingRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Booking not found"));
        return new ApiResponse<>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<Booking> addBooking(BookingRequest bookingRequest){
        if (!userRepository.existsById(bookingRequest.getUserId())){
            throw new RuntimeException("User not found");
        }

        if (!tourDateRepository.existsById(bookingRequest.getTourDateId())){
            throw new RuntimeException("TourDate not found");
        }

        Booking entity = bookingMapper.toBooking(bookingRequest);
        entity.setStatus(StatusBooking.PENDING);
        bookingRepository.save(entity);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Booking Success",entity);
    }

    public ApiResponse<Booking> updateStatus(String id,String status){
        Booking entity = bookingRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Booking not found"));
        switch (status){
            case "confirm" :
                entity.setStatus(StatusBooking.CONFIRMED);
                break;
            case "complete" :
                entity.setStatus(StatusBooking.COMPLETED);
                break;
            case "cancelled" :
                entity.setStatus(StatusBooking.CANCELLED);
                break;
            default:
                throw new RuntimeException("Action not valid");
        }
        bookingRepository.save(entity);
        return new ApiResponse<>(HttpStatus.OK.value(), "Update success",entity);
    }

    public ApiResponse<Void> deleteBooking(String id){
        Booking entity = bookingRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Booking not found"));
        bookingRepository.delete(entity);
        return new ApiResponse<>(HttpStatus.OK.value(), "Delete success");
    }
}
