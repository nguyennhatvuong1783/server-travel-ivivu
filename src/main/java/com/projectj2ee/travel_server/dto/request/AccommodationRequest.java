package com.projectj2ee.travel_server.dto.request;

import jakarta.validation.constraints.Max;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationRequest {
    private String name;

    private String type;

    private String address;

    private String description;

    @Max(value = 5, message = "Value must not exceed 5")
    private int rating;

    private Boolean status;
}
