package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.TourDateRequest;
import com.projectj2ee.travel_server.entity.TourDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourDateMapper {
    @Mapping(source = "packageId", target = "tourPackage.id")
    TourDate toTourDate(TourDateRequest tourDateRequest);
}
