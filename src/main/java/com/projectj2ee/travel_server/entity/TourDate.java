package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tour_dates")
public class TourDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_date_id")
    int id;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id",nullable = false)
    TourPackage tourPackage;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @Column(name = "available_spots")
    int spots;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean status;


}
