package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.ActivityRequest;
import com.projectj2ee.travel_server.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(target = "id", ignore = true)
    Activity activityRequestToActivity(ActivityRequest activityRequest);
}
