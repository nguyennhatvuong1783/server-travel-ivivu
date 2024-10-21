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
@Table(name = "price_details")
public class PriceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_detail_id")
    private int id;

    @Column(name = "included_price")
    private String include;

    @Column(name = "excluded_price")
    private String exclude;

    @Column
    private String surcharge;

    @Column(name = "cancellation_policy")
    private String policy;

    @Column
    private String notes;

    @Column
    private Boolean status;
}
