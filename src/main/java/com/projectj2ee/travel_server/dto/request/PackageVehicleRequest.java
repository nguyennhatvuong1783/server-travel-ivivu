package com.projectj2ee.travel_server.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PackageVehicleRequest {
    private int tourPackage;

    private int vehicle;
}
