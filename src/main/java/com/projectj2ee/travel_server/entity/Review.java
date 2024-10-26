package com.projectj2ee.travel_server.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    int id;

    @Column(name = "user_id", nullable = false)
    int userId;

    @Column(name = "package_id",nullable = false)
    int packageId;

    @Column
    int rating;

    @Column
    String comment;

    @Column(name = "review_date")
    Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Column
    Boolean status ;
}
