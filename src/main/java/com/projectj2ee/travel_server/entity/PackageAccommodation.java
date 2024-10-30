package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.PackageAccommodationIds;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name = "package_accommodations")
public class PackageAccommodation {

    @EmbeddedId
    PackageAccommodationIds id;

    @Column(name = "nights_stay") // Duration trong bảng TourPackge = sum(nightStay) + 1
    int nightStay;
}
