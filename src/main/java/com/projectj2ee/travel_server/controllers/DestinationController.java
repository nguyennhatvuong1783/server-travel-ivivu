package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.DestinationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Destination;
import com.projectj2ee.travel_server.service.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
@AllArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping("/")
    public ApiResponse<List<Destination>> getAllDestination(){
        return destinationService.getAllDestination();
    }

    @GetMapping("/{id}")
    public ApiResponse<Destination> getDestinationById(@PathVariable("id") int id){
        return destinationService.getDestinationById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Destination> createDestination(@RequestBody DestinationRequest destinationRequest){
        return destinationService.addDestination(destinationRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Destination> editDestination(@PathVariable("id") int id, @RequestBody DestinationRequest destinationRequest){
        return destinationService.editDestination(id, destinationRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Destination> deleteDestination(@PathVariable("id") int id){
        return destinationService.deleteDestination(id);
    }
}
