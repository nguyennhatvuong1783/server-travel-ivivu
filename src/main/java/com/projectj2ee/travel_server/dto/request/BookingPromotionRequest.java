package com.projectj2ee.travel_server.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingPromotionRequest {
    int bookingId;
    int promotionId;
}
