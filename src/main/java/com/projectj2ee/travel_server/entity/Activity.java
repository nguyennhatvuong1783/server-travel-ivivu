package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.enums.ActivityLevel;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private ActivityLevel level;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;
}
