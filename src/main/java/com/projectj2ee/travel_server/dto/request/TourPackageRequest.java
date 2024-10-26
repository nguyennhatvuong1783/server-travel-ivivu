package com.projectj2ee.travel_server.dto.request;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Company ID cannot be null")
    private int companyId; // Chỉ cần ID của công ty
    @NotNull(message = "Price Detail ID cannot be null")
    private int priceDetailId; // Chỉ cần ID của chi tiết giá

}
