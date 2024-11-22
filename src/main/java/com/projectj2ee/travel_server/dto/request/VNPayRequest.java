package com.projectj2ee.travel_server.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayRequest {
    int price;

    String orderInfo;

    int bookingId;
}
