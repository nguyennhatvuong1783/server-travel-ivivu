package com.projectj2ee.travel_server.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String name;

    private String description;

    private Boolean status;
}
