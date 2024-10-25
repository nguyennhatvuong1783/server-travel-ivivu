package com.projectj2ee.travel_server.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourFeatureRequest {
    String name;

    String description;
}
