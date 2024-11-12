package com.projectj2ee.travel_server.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDto {
    String email;
    String tourName;
    String tourId;
    String duration;
    String participant;
    String startDate;
    String endDate;
    String name;
    String phone;
    String pricePackage;
    String totalPrice;
}
