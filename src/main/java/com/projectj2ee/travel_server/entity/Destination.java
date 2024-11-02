package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private int id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String description;

    @Column
    private String climate;

    @Column(name = "best_time_to_visit")
    private String bestTime;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;
}
