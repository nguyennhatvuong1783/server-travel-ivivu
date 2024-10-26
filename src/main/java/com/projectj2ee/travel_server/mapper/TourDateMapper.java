package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.TourDateRequest;
import com.projectj2ee.travel_server.entity.TourDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourDateMapper {
    TourDate toTourDate(TourDateRequest tourDateRequest);
}
