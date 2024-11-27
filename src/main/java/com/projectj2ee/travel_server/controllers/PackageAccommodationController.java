package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PackageAccommodationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.service.PackageAccommodationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-accommodation")
@AllArgsConstructor
public class PackageAccommodationController {
    private final PackageAccommodationService packageAccommodationService;

    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody PackageAccommodationRequest request){
        return packageAccommodationService.add(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PackageAccommodationRequest request){
        packageAccommodationService.delete(request);
        return ResponseEntity.noContent().build();
    }

}
