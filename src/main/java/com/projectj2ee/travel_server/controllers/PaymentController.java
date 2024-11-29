package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PaymentRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.dto.response.VNPayResponse;
import com.projectj2ee.travel_server.entity.Payment;
import com.projectj2ee.travel_server.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/payment")
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

    @GetMapping("/vnpay")
    public VNPayResponse pay(HttpServletRequest request, @RequestBody PaymentRequest paymentRequest){
        return paymentService.createVnPayPayment(request, paymentRequest);
    }

    @PutMapping("/confirm/{bookingId}")
    public ApiResponse<String> confirmPayment(@RequestBody String transactionNo, @PathVariable("bookingId") int bookingId){
        return paymentService.confirmPayment(bookingId, transactionNo);
    }

    @GetMapping("/vn-pay-callback")
    public ApiResponse<String> payCallbackHandler(HttpServletRequest request){
        return paymentService.orderReturn(request);
    }

    @PostMapping("/zalopay")
    public ApiResponse<String> createZaloPay(@RequestBody PaymentRequest request) throws Exception {
        return paymentService.createZalopayPayment(request);
    }

    @PostMapping("/zalo-pay-callback")
    public ApiResponse<String> callback(@RequestBody String request) throws Exception {
        return paymentService.callbackZaloPay(request);
    }


}
