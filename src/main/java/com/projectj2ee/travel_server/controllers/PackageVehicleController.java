package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PackageVehicleRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.service.PackageVehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-vehicle")
@AllArgsConstructor
public class PackageVehicleController {
    private final PackageVehicleService packageVehicleService;

    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody PackageVehicleRequest request){
        return packageVehicleService.add(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PackageVehicleRequest request){
        packageVehicleService.delete(request);
        return ResponseEntity.noContent().build();
    }
}
