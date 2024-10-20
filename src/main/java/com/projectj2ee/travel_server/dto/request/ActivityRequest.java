package com.projectj2ee.travel_server.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private String name;

    private String description;

    private int duration;

    @Pattern(regexp = "^(Dễ|Trung bình|Khó)$", message = "levels must be one of: 'Dễ', 'Trung bình', or 'Khó'")
    private String level;

    private Boolean status;
}
