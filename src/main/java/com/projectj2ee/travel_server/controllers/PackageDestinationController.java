package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PackageDestinationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.service.PackageDestinationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-destination")
@AllArgsConstructor
public class PackageDestinationController {
    private final PackageDestinationService packageDestinationService;

    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody PackageDestinationRequest request){
        return  packageDestinationService.add(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PackageDestinationRequest request){
        packageDestinationService.delete(request);
        return ResponseEntity.noContent().build();
    }
}
