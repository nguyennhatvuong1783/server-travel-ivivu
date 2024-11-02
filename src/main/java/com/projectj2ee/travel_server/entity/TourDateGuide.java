package com.projectj2ee.travel_server.entity;

import com.projectj2ee.travel_server.dto.Ids.TourDateGuideIds;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name = "tour_date_guides")
public class TourDateGuide {
   @EmbeddedId
   TourDateGuideIds id;
}
