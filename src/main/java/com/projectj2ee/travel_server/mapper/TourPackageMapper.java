package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.TourPackageRequest;
import com.projectj2ee.travel_server.entity.TourPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourPackageMapper {
    TourPackage toEntity(TourPackageRequest tourPackageRequest);
}
