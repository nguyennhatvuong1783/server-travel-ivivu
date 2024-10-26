package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.TourDateRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourDate;
import com.projectj2ee.travel_server.service.TourDateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tour-date")
@AllArgsConstructor
public class TourDateController {
    private final TourDateService tourDateService;

    @GetMapping("/")
    public ApiResponse<List<TourDate>> getAllTourDate(){
        return tourDateService.getAllTourDate();
    }

    @GetMapping("/{id}")
    public ApiResponse<TourDate> getTourDateById(@PathVariable("id") String id){
        return tourDateService.getTourDateById(id);
    }

    @PostMapping("/create")
    public ApiResponse<TourDate> createTourDate(@RequestBody @Valid TourDateRequest tourDateRequest){
        return tourDateService.addTourDate(tourDateRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<TourDate> updateTourDate(@PathVariable("id") String id,@RequestBody @Valid TourDateRequest tourDateRequest){
        return tourDateService.editTourDate(id, tourDateRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<TourDate> deleteTourDate(@PathVariable("id") String id){
        return tourDateService.deleteTourDateById(id);
    }

}
