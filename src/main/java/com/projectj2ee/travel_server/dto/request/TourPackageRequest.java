package com.projectj2ee.travel_server.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

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
    private String depart;
    @NotNull(message = "Company ID cannot be null")
    private int companyId;
    private Set<Integer> tourFeatureId;
    private Set<Integer> vehicleId;
    private Set<Integer> destinationId;
    private Set<Integer> activityId;
    private Set<Integer> accommodationId;
}
