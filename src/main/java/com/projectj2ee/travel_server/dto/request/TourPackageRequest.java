package com.projectj2ee.travel_server.dto.request;

import com.projectj2ee.travel_server.entity.*;
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
    @NotNull(message = "Company ID cannot be null")
    private int companyId; // Chỉ cần ID của công ty
    private Set<TourFeature> tourFeature;
    private Set<Vehicle> vehicles;
    private Set<Destination> destinations;
    private Set<Activity> activities;
    private Set<Accommodation> accommodations;
}
