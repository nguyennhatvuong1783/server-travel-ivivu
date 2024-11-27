package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.PackageActivityIds;
import com.projectj2ee.travel_server.dto.request.PackageActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PackageActivity;
import com.projectj2ee.travel_server.repository.ActivityRepository;
import com.projectj2ee.travel_server.repository.PackageActivityRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageActivityService {
    private final TourPackageRepository tourPackageRepository;

    private final ActivityRepository activityRepository;

    private final PackageActivityRepository packageActivityRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> add(PackageActivityRequest request){
        if (!tourPackageRepository.existsById(request.getTourPackage())) throw  new RuntimeException("User not found");
        if (!tourPackageRepository.existsById(request.getActivity())) throw new RuntimeException("Activity not found");
        PackageActivity entity = new PackageActivity(
                new PackageActivityIds(request.getTourPackage(), request.getActivity()),
                request.isIncluded()
        );
        packageActivityRepository.save(entity);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Success");

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(PackageActivityRequest request){
        PackageActivity entity = new PackageActivity(
                new PackageActivityIds(request.getTourPackage(), request.getActivity()),
                request.isIncluded()
        );
        packageActivityRepository.save(entity);
        packageActivityRepository.delete(entity);
    }
}
