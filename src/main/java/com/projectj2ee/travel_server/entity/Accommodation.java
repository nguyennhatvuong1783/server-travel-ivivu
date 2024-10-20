package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String address;

    @Column
    private String description;

    @Column
    private int rating;

    @Column
    private Boolean status;
}
