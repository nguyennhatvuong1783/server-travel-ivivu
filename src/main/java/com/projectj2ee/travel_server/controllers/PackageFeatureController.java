package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.PackageFeatureRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.service.PackageFeatureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-feature")
@AllArgsConstructor
public class PackageFeatureController {
    private final PackageFeatureService packageFeatureService;

    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody PackageFeatureRequest request){
        return packageFeatureService.addPackageFeature(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PackageFeatureRequest request){
        packageFeatureService.delete(request);
        return ResponseEntity.noContent().build();
    }
}
