package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.DestinationRequest;
import com.projectj2ee.travel_server.entity.Destination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    Destination toDestination(DestinationRequest destinationRequest);
}
