package com.projectj2ee.travel_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private String vehicle_name;

    private String description;

    private Boolean status;
}
