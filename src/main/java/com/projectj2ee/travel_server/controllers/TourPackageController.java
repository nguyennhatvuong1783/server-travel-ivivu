package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.TourPackageRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourPackage;
import com.projectj2ee.travel_server.service.TourPackageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour-package")
@AllArgsConstructor
public class TourPackageController {
    private final TourPackageService tourPackageService;

    @GetMapping("/")
    public ApiResponse<List<TourPackage>> getAllTourPackage(){
        return tourPackageService.getAllTourPackage();
    }

    @GetMapping("/{id}")
    public ApiResponse<TourPackage> getTourPackageById(@PathVariable("id") String id){
        return tourPackageService.getTourPackageById(id);
    }

    @PostMapping("/create")
    public ApiResponse<TourPackage> createTourPackage(@RequestBody TourPackageRequest tourPackageRequest){
        return tourPackageService.addTourPackge(tourPackageRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<TourPackage> editTourPackage(@PathVariable("id") String id, @RequestBody TourPackageRequest tourPackageRequest){
        return tourPackageService.editTourPackage(id, tourPackageRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<TourPackage> deleteTourPackage(@PathVariable("id") String id){
        return tourPackageService.deleteTourPackage(id);
    }
}
