package com.projectj2ee.travel_server.dto.request;

import com.projectj2ee.travel_server.dto.enums.ActivityLevel;
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

    @Pattern(regexp = "^(EASY|NORMAL|DIFFICULT)$", message = "levels must be one of: 'EASY', 'NORMAL', or 'DIFFICULT'")
    private ActivityLevel level;

    private Boolean status;
}
