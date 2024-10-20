package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.AccommodationRequest;
import com.projectj2ee.travel_server.dto.request.ActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Accommodation;
import com.projectj2ee.travel_server.service.AccommodationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodation")
@AllArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;

    @GetMapping("/")
    public ApiResponse<List<Accommodation>> getAllAccommodation(){
        return accommodationService.getAllAccommodation();
    }

    @GetMapping("/{id}")
    public ApiResponse<Accommodation> getAccommodationById(@PathVariable("id") String id){
        return accommodationService.getAccommodationById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Accommodation> createAccommodation(@RequestBody @Valid AccommodationRequest accommodationRequest){
        return accommodationService.addAccommodation(accommodationRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Accommodation> updateAccommodation(@PathVariable("id") String id, @RequestBody @Valid AccommodationRequest accommodationRequest){
        return accommodationService.updateAccommodation(id,accommodationRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Accommodation> deleteAccommodation(@PathVariable("id") String id){
        return accommodationService.deletedAccommodation(id);
    }

}
