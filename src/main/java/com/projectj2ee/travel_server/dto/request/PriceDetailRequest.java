package com.projectj2ee.travel_server.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
public class PriceDetailRequest {
    private String include;

    private String exclude;

    private String surcharge;

    private String policy;

    private String notes;

}
