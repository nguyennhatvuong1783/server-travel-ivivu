package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.entity.Payment;
import com.projectj2ee.travel_server.mapper.PaymentMapper;
import com.projectj2ee.travel_server.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

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


}
