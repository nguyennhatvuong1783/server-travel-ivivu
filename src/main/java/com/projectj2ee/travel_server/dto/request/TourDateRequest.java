package com.projectj2ee.travel_server.dto.request;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourDateRequest {
    @NotNull(message = "Package ID cannot be null")
    int packageId;

    Date startDate;

    Date endDate;

    int spots;
}
