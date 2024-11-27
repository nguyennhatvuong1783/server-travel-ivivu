package com.projectj2ee.travel_server.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PackageActivityRequest {
    private int tourPackage;
    private int activity;
    private boolean included;
}
