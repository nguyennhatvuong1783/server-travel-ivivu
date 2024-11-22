package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import com.projectj2ee.travel_server.dto.enums.StatusPayment;
import com.projectj2ee.travel_server.dto.request.PaymentRequest;
import com.projectj2ee.travel_server.dto.request.VNPayRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.dto.response.VNPayResponse;
import com.projectj2ee.travel_server.entity.Booking;
import com.projectj2ee.travel_server.entity.Payment;
import com.projectj2ee.travel_server.mapper.PaymentMapper;
import com.projectj2ee.travel_server.repository.BookingRepository;
import com.projectj2ee.travel_server.repository.PaymentRepository;
import com.projectj2ee.travel_server.service.config.VNPayConfig;
import com.projectj2ee.travel_server.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;
    private final VNPayConfig vnPayConfig;

    @PreAuthorize("hasAuthority('ADMIN')")
    public PageResponse<Payment> getAllPayment(int size, int page){
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,size,sort);
        var pageData = paymentRepository.findAll(pageable);

        return PageResponse.<Payment>builder()
                .statusCode(HttpStatus.OK.value())
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().toList())
                .build();
    }

    @PreAuthorize("hasAuthority('USER', 'ADMIN')")
    public ApiResponse<Payment> addPayment(PaymentRequest paymentRequest){
        Payment payment = paymentMapper.toPayment(paymentRequest);
        payment.setStatus(StatusPayment.PENDING);
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
                .orElseThrow(()->new RuntimeException("Booking not found"));
        paymentRepository.save(payment);
        return new ApiResponse<Payment>(HttpStatus.CREATED.value(), "Create success", payment);
    }

    @PreAuthorize("hasAuthority('USER')")
    public ApiResponse<String> confirmPayment(int bookingid, String transactionNo){
        Optional<Payment> paymentOpt = paymentRepository.findByBooking_Id(bookingid);
        if (paymentOpt.isPresent()){
            if (transactionNo.equalsIgnoreCase(paymentOpt.get().getTransaction())){
                Payment payment = paymentOpt.orElseGet(Payment::new);
                Booking booking = payment.getBooking();
                int compare = payment.getAmount().compareTo(booking.getTotalPrice());
                if (compare == 0){
                    payment.setStatus(StatusPayment.PAID);
                    booking.setStatus(StatusBooking.COMPLETED);
                } else if (compare < 0) {
                    payment.setStatus(StatusPayment.PENDING);
                }else {
                    payment.setStatus(StatusPayment.OVER);
                    throw new RuntimeException("overpayment");
                }
                paymentRepository.save(payment);
                bookingRepository.save(booking);
                return new ApiResponse<>(HttpStatus.CREATED.value(), "Create success");
            }
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Error");
    }

    @PreAuthorize("hasAuthority('USER')")
    public VNPayResponse createVnPayPayment(VNPayRequest vnPayRequest, HttpServletRequest request, PaymentRequest paymentRequest){
        Map<String,String> vnpParamsMap = vnPayConfig.getVNPayConfig(vnPayRequest.getOrderInfo(), vnPayRequest.getPrice());
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));

        // build query Url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(),hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;

        addPayment(paymentRequest);
        return VNPayResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Success")
                .bookingId(paymentRequest.getBookingId())
                .paymentUrl(paymentUrl)
                .build();
    }

    public ApiResponse<String> orderReturn(HttpServletRequest request){
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = vnPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return new ApiResponse<>(HttpStatus.OK.value(), "success");
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "fail");
            }
        } else {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "error");
        }

    }

}
