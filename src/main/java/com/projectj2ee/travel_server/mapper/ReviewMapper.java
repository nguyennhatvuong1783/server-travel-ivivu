package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.ReviewRequest;
import com.projectj2ee.travel_server.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "packageId", target = "tourPackage.id")
    Review toReview(ReviewRequest request);
}
