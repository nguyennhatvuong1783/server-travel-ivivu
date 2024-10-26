package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.ReviewRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Review;
import com.projectj2ee.travel_server.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/package/{id}")
    public ApiResponse<List<Review>> getAllReviewByPackageId(@PathVariable("id") String id){
        return reviewService.getAllReviewByPackageId(id);
    }

    @PostMapping("/create")
    public ApiResponse<Review> createReview(@RequestBody @Valid ReviewRequest request){
        return reviewService.createReview(request);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteReview(@PathVariable("id") String id){
        return reviewService.deleteReview(id);
    }

}
