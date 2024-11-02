package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_guides")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_id")
    int id;

    @Column(name = "full_name")
    String name;

    @Column
    String email;

    @Column(name = "phone_number")
    String phone;

    @Column
    String languages;

    @Column
    int experience;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean status;
}
