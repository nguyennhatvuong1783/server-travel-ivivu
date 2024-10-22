package com.projectj2ee.travel_server.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TourPackageRequest {
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
    private int count;
    private int companyId; // Chỉ cần ID của công ty
    private int priceDetailId; // Chỉ cần ID của chi tiết giá

}
