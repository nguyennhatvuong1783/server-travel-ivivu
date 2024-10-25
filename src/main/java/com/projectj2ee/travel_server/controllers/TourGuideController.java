package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.TourGuideRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourGuide;
import com.projectj2ee.travel_server.service.TourGuideService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour-guide")
@AllArgsConstructor
public class TourGuideController {
    private final TourGuideService tourGuideService;

    @GetMapping("/")
    public ApiResponse<List<TourGuide>> getAllTourGuide(){
        return tourGuideService.getAllTourGuide();
    }

    @GetMapping("/{id}")
    public ApiResponse<TourGuide> getTourGuideById(@PathVariable("id") String id){
        return tourGuideService.getTourGuideById(id);
    }

    @PostMapping("/create")
    public ApiResponse<TourGuide> createTourGuide(@RequestBody @Valid TourGuideRequest tourGuideRequest){
        return tourGuideService.addTourGuide(tourGuideRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<TourGuide> editTourGuide(@PathVariable("id") String id,@RequestBody @Valid TourGuideRequest tourGuideRequest){
        return tourGuideService.editTourGuide(id, tourGuideRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<TourGuide> deleteTourGuide(@PathVariable("id") String id){
        return tourGuideService.deleteTourGuide(id);
    }
}
