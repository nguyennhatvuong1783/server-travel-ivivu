package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.PromotionRequest;
import com.projectj2ee.travel_server.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toPromotion(PromotionRequest promotionRequest);
}
