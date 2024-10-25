package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.TourGuideRequest;
import com.projectj2ee.travel_server.entity.TourGuide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourGuideMapper {
    TourGuide toTourGuide(TourGuideRequest tourGuideRequest);
}
