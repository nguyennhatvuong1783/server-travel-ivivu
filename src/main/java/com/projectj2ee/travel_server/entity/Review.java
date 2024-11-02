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

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id",nullable = false)
    TourPackage tourPackage;

    @Column
    int rating;

    @Column
    String comment;

    @Column(name = "review_date")
    Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean status ;
}
