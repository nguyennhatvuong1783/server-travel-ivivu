package com.projectj2ee.travel_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VNPayResponse {
    public int code;
    public String message;
    public int bookingId;
    public String paymentUrl;
}
