package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tour_features")
public class TourFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    int id;

    @Column(name = "feature_name")
    String name;

    @Column
    String description;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean status;

}
