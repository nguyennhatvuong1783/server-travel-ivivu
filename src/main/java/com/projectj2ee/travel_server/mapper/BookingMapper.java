package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.BookingRequest;
import com.projectj2ee.travel_server.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "tourDateId", target = "tourDate.id")
    @Mapping(source = "userId", target = "user.id")
    Booking toBooking(BookingRequest bookingRequest);
}
