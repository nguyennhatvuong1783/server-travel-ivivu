package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PackageActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.service.PackageActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-activity")
@AllArgsConstructor
public class PackageActivityController {
    private final PackageActivityService packageActivityService;

    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody PackageActivityRequest request){
        return packageActivityService.add(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PackageActivityRequest request){
        packageActivityService.delete(request);
        return ResponseEntity.noContent().build();
    }
}
