package com.projectj2ee.travel_server.dto.request;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    int userId;

    int tourDateId;

    int participants;

    BigDecimal totalPrice;

}
