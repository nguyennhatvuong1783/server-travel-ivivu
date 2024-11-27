package com.projectj2ee.travel_server.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PackageAccommodationRequest {
    private int tourPackage;

    private int accommodation;

    private int nightStay;
}
