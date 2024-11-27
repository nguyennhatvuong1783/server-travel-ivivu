package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.PackageFeatureIds;
import com.projectj2ee.travel_server.dto.request.PackageFeatureRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PackageFeature;
import com.projectj2ee.travel_server.repository.PackageFeatureRepository;
import com.projectj2ee.travel_server.repository.TourFeatureRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageFeatureService {
    private final TourPackageRepository tourPackageRepository;

    private final TourFeatureRepository tourFeatureRepository;

    private final PackageFeatureRepository packageFeatureRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> addPackageFeature(PackageFeatureRequest request){
        if (!tourPackageRepository.existsById(request.getTourPackage())){
            throw new RuntimeException("Tour package not found");
        }
        if (!tourFeatureRepository.existsById(request.getTourFeature())){
            throw new RuntimeException("Tour feature not found");
        }

        PackageFeature packageFeature = new PackageFeature();
        packageFeature.setId(new PackageFeatureIds(request.getTourPackage(), request.getTourFeature()));
        packageFeatureRepository.save(packageFeature);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(PackageFeatureRequest request){
        PackageFeature entity = new PackageFeature(new PackageFeatureIds(request.getTourPackage(), request.getTourFeature()));
        packageFeatureRepository.delete(entity);
    }

}
