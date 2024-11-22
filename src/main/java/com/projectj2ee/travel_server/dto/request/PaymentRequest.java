package com.projectj2ee.travel_server.dto.request;

import com.projectj2ee.travel_server.dto.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    int id;

    int bookingId;

    BigDecimal amount;

    PaymentMethod method;

    String transaction;

}
