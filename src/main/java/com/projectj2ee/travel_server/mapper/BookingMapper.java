package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.BookingRequest;
import com.projectj2ee.travel_server.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingRequest bookingRequest);
}
