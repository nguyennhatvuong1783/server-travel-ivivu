package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PromotionRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Promotion;
import com.projectj2ee.travel_server.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@AllArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("/")
    public ApiResponse<List<Promotion>> getAllPromotion(){
        return promotionService.getAllPromotion();
    }

    @GetMapping("/{id}")
    public ApiResponse<Promotion> getPromotionById(@PathVariable("id") String id){
        return promotionService.getPromotionById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Promotion> createPromotion(@RequestBody PromotionRequest promotionRequest){
        return promotionService.addPromotion(promotionRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Promotion> updatePromotion(@PathVariable("id") String id, @RequestBody PromotionRequest promotionRequest){
        return promotionService.editPromotion(id, promotionRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Promotion> deletePromotion(@PathVariable("id") String id){
        return promotionService.deletePromotion(id);
    }


}
