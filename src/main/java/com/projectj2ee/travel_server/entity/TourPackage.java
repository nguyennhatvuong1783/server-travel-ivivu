package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_packages")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private int id;

    @Column(unique = true, name = "tour_code")
    private String tourCode;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int duration;

    @Column(name = "depart_from")
    private String depart;

    @Column
    private String image;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "max_participants")
    private int count;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id",nullable = false)
    private Company company;

    @Column(name = "price_detail")
    private String priceDetail;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "package_features",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private Set<TourFeature> tourFeature;

    @ManyToMany
    @JoinTable(
            name = "package_vehicles",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private Set<Vehicle> vehicles;

    @ManyToMany
    @JoinTable(
            name = "package_destinations",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id")
    )
    private Set<Destination> destinations;

    @ManyToMany
    @JoinTable(
            name = "package_activities",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities;

    @ManyToMany
    @JoinTable(
            name = "package_accommodations",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "accommodation_id")
    )
    private Set<Accommodation> accommodations;
}
