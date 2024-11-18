package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.ReviewRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Review;
import com.projectj2ee.travel_server.mapper.ReviewMapper;
import com.projectj2ee.travel_server.repository.ReviewRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    private final UserRepository userRepository;

    public ApiResponse<List<Review>> getAllReviewByPackageId(String packageId){
        return new ApiResponse<>(HttpStatus.OK.value(), "Success",reviewRepository.findByTourPackage_id(Integer.parseInt(packageId)));
    }

    public ApiResponse<Review> createReview(ReviewRequest request){
        if (!tourPackageRepository.existsById(request.getPackageId()) || !userRepository.existsById(request.getUserId())){
            throw new RuntimeException("TourPackage or User not found");
        }
        Review entity = reviewMapper.toReview(request);
        entity.setStatus(true);
        reviewRepository.save(entity);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    public ApiResponse<Void> deleteReview(int id){
        Review entity = reviewRepository.findById(id).orElseThrow(()->new RuntimeException("Review not found"));
        reviewRepository.delete(entity);
        return new ApiResponse<>(HttpStatus.OK.value(), "Deleted");
    }

}
