package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.PriceDetailRequest;
import com.projectj2ee.travel_server.entity.PriceDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PriceDetailMapper {
    @Mapping(source = "include", target = "include")
    @Mapping(source = "exclude", target = "exclude")
    @Mapping(source = "policy", target = "policy")
    PriceDetail toPriceDetail(PriceDetailRequest priceDetailRequest);
}
