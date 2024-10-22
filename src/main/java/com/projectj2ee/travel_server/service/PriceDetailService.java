package com.projectj2ee.travel_server.service;


import com.projectj2ee.travel_server.dto.request.PriceDetailRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PriceDetail;
import com.projectj2ee.travel_server.mapper.PriceDetailMapper;
import com.projectj2ee.travel_server.repository.PriceDetailRepository;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceDetailService {
    @Autowired
    private final PriceDetailRepository priceDetailRepository;


    @Autowired
    private final PriceDetailMapper priceDetailMapper;


    public ApiResponse<List<PriceDetail>> getAllPriceDetail(){
        return new ApiResponse<List<PriceDetail>>(HttpStatus.OK.value(), "Success",priceDetailRepository.findAll());
    }

    public ApiResponse<PriceDetail> getPriceDetailById(String id){
        PriceDetail entity = priceDetailRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Price detail not found"));
        return new ApiResponse<PriceDetail>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<PriceDetail> addPriceDetail(PriceDetailRequest priceDetailRequest){
        PriceDetail entity = priceDetailMapper.toPriceDetail(priceDetailRequest);
        entity.setStatus(true);
        priceDetailRepository.save(entity);
        return new ApiResponse<PriceDetail>(HttpStatus.CREATED.value(), "Create success",entity);
    }

    public ApiResponse<PriceDetail> editPriceDetail(String id,PriceDetailRequest priceDetailRequest){
        PriceDetail entity = priceDetailRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Price detail not found"));
        entity = priceDetailMapper.toPriceDetail(priceDetailRequest);
        entity.setId(Integer.parseInt(id));
        entity.setStatus(true);
        priceDetailRepository.save(entity);
        return new ApiResponse<PriceDetail>(HttpStatus.OK.value(), "Update Success",entity);
    }

    public ApiResponse<PriceDetail> deletePriceDetail(String id){
        PriceDetail entity = priceDetailRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Price detail not found"));
        entity.setStatus(false);
        priceDetailRepository.save(entity);
        return new ApiResponse<PriceDetail>(HttpStatus.OK.value(), "Delete Success",entity);
    }


}
