package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.PaymentRequest;
import com.projectj2ee.travel_server.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "bookingId", target = "booking.id")
    Payment toPayment(PaymentRequest paymentRequest);
}
