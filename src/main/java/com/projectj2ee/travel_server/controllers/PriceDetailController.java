package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PriceDetailRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PriceDetail;
import com.projectj2ee.travel_server.service.PriceDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priceDetail")
@AllArgsConstructor
public class PriceDetailController {
    private final PriceDetailService priceDetailService;

    @GetMapping("/")
    public ApiResponse<List<PriceDetail>> getAllPriceDetail(){
        return priceDetailService.getAllPriceDetail();
    }

    @GetMapping("/{id}")
    public ApiResponse<PriceDetail> getPriceDetailById(@PathVariable("id") String id){
        return priceDetailService.getPriceDetailById(id);
    }

    @PostMapping("/create")
    public ApiResponse<PriceDetail> createPriceDetail(@RequestBody PriceDetailRequest priceDetailRequest){
        return priceDetailService.addPriceDetail(priceDetailRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<PriceDetail> editPriceDetail(@PathVariable("id") String id,@RequestBody PriceDetailRequest priceDetailRequest){
        return priceDetailService.editPriceDetail(id, priceDetailRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<PriceDetail> deletePriceDetail(@PathVariable("id") String id){
        return priceDetailService.deletePriceDetail(id);
    }
}
