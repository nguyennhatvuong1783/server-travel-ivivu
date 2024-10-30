package com.projectj2ee.travel_server.dto.Ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TourDateGuideIds implements Serializable {
    @Column(name = "tour_date_id")
    int tourDateId;

    @Column(name = "guide_id")
    int tourGuideId;
}
