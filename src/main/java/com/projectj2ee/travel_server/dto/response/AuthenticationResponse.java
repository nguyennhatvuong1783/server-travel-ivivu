package com.projectj2ee.travel_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    final private String jwt;

}
