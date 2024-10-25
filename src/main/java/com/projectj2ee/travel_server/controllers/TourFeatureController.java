package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.TourFeatureRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourFeature;
import com.projectj2ee.travel_server.service.TourFeatureService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour-feature")
@AllArgsConstructor
public class TourFeatureController {
    private final TourFeatureService tourFeatureService;

    @GetMapping("/")
    public ApiResponse<List<TourFeature>> getAllTourFeature(){
        return tourFeatureService.getAllTourFeature();
    }

    @GetMapping("/{id}")
    public ApiResponse<TourFeature> getTourFeatureById(@PathVariable("id") String id){
        return tourFeatureService.getTourFeatureById(id);
    }

    @PostMapping("/create")
    public ApiResponse<TourFeature> createTourFeature(@RequestBody TourFeatureRequest tourFeatureRequest){
        return tourFeatureService.addTourFeature(tourFeatureRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<TourFeature> updateTourFeature(@PathVariable("id") String id,@RequestBody TourFeatureRequest tourFeatureRequest){
        return tourFeatureService.editTourFeature(id, tourFeatureRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<TourFeature> deleteTourFeature(@PathVariable("id") String id){
        return tourFeatureService.deleteTourFeature(id);
    }
}
