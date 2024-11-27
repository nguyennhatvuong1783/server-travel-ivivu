package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.PackageVehicleIds;
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
@Table(name = "package_vehicles")
public class PackageVehicle {
    @EmbeddedId
    PackageVehicleIds id;
}
