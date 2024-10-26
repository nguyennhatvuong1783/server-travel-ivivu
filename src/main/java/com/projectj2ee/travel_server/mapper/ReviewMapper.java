package com.projectj2ee.travel_server.mapper;

import com.projectj2ee.travel_server.dto.request.ReviewRequest;
import com.projectj2ee.travel_server.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewRequest request);
}
