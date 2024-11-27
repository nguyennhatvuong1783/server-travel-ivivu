package com.projectj2ee.travel_server.dto.request;

import com.projectj2ee.travel_server.dto.enums.PromotionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionRequest {
    String code;

    String description;

    PromotionType type;

    BigDecimal discount;

    Date start;

    Date end;

}
