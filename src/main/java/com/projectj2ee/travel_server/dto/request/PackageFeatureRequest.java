package com.projectj2ee.travel_server.dto.request;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PackageFeatureRequest {
    private int tourPackage;

    private int tourFeature;
}
