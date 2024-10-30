package com.projectj2ee.travel_server.dto.Ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PackageAccommodationIds implements Serializable {
    @Column(name = "package_id")
    private int packageId;

    @Column(name = "accommodation_id")
    private int accommodationId;
}
