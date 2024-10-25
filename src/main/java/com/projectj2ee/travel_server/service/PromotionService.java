package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.PromotionRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Promotion;
import com.projectj2ee.travel_server.mapper.PromotionMapper;
import com.projectj2ee.travel_server.repository.PromotionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {
    @Autowired
    private final PromotionRepository promotionRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    public ApiResponse<List<Promotion>> getAllPromotion(){
        return new ApiResponse<List<Promotion>>(HttpStatus.OK.value(), "Success",promotionRepository.findAll());
    }

    public ApiResponse<Promotion> getPromotionById(String id){
        Promotion entity = promotionRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RuntimeException("Promotion not found"));
        return new ApiResponse<Promotion>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<Promotion> addPromotion(PromotionRequest promotionRequest){
        Promotion entity = new Promotion();
        entity = promotionMapper.toPromotion(promotionRequest);
        entity.setStatus(true);
        promotionRepository.save(entity);
        return new ApiResponse<Promotion>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    public ApiResponse<Promotion> editPromotion(String id,PromotionRequest promotionRequest){
        Promotion entity = promotionRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RuntimeException("Promotion not found"));
        entity = promotionMapper.toPromotion(promotionRequest);
        entity.setId(Integer.parseInt(id));
        entity.setStatus(true);
        promotionRepository.save(entity);
        return new ApiResponse<Promotion>(HttpStatus.OK.value(), "Update Success",entity);
    }

    public ApiResponse<Promotion> deletePromotion(String id){
        Promotion entity = promotionRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RuntimeException("Promotion not found"));
        entity.setStatus(false);
        promotionRepository.save(entity);
        return new ApiResponse<Promotion>(HttpStatus.OK.value(), "Success",entity);
    }


}
