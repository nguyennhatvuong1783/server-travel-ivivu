package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.AccommodationRequest;
import com.projectj2ee.travel_server.entity.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccommodationMapper {
    @Mapping(target = "id", ignore = true)
    Accommodation accommodationRequestToAccommodation(AccommodationRequest accommodationRequest);
}
