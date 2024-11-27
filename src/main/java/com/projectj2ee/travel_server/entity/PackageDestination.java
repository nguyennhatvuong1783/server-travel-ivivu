package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.PackageDestinationIds;
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
@Table(name = "package_destinations")
public class PackageDestination {
    @EmbeddedId
    PackageDestinationIds id;

    @Column(name = "visit_order")
    int visitOrder;
}
