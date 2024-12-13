package com.projectj2ee.travel_server.dto.response;

import com.projectj2ee.travel_server.entity.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourPackageResponse {
    private int id;
    private String name;
    private String  tourCode;
    private String description;
    private int duration;
    private BigDecimal price;
    private int count;
    private String depart;
    private List<String> imageUrl;
    private Company companyId;
    private Set<TourFeature> tourFeature;
    private Set<Vehicle> vehicles;
    private Set<Destination> destinations;
    private Set<Activity> activities;
    private Set<Accommodation> accommodations;
    private Boolean status;
}
