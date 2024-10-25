package com.projectj2ee.travel_server.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourGuideRequest {
    String name;

    String email;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    String phone;

    String languages;

    int experience;
}
