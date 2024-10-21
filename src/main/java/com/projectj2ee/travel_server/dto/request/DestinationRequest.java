package com.projectj2ee.travel_server.dto.request;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {
    private String name;

    private String country;

    private String description;

    private String climate;

    private String bestTime;

}
