package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicle_id;

    @Column(name = "vehicle_name")
    private String name;

    @Column
    private String description;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;
}
