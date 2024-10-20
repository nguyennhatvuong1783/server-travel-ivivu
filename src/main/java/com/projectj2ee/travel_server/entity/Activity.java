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
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int duration;

    @Column(name = "difficulty_level")
    private String level;

    @Column
    private Boolean status;
}
