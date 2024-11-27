package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.PackageAccommodationIds;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "package_accommodations")
public class PackageAccommodation {

    @EmbeddedId
    PackageAccommodationIds id;

    @Column(name = "nights_stay")
    int nightStay;

}
