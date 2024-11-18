package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PaymentRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.entity.Payment;
import com.projectj2ee.travel_server.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/")
    public PageResponse<Payment> getAllPayment(
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "size", required = false,defaultValue = "10") int size
    ){
        return paymentService.getAllPayment(page, size);
    }

    @PostMapping("/cash")
    public ApiResponse<Payment> castPayment(PaymentRequest paymentRequest){
        return paymentService.addPayment(paymentRequest);
    }


}
