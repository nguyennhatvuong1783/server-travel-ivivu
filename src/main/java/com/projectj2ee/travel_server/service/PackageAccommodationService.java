package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.PackageAccommodationIds;
import com.projectj2ee.travel_server.dto.request.PackageAccommodationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PackageAccommodation;
import com.projectj2ee.travel_server.repository.AccommodationRepository;
import com.projectj2ee.travel_server.repository.PackageAccommodationRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageAccommodationService {
    private final TourPackageRepository tourPackageRepository;

    private final AccommodationRepository accommodationRepository;

    private final PackageAccommodationRepository packageAccommodationRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> add(PackageAccommodationRequest request){
        if (!tourPackageRepository.existsById(request.getTourPackage())) throw new RuntimeException("Tour package not found");
        if (!accommodationRepository.existsById(request.getAccommodation())) throw new RuntimeException("Accommodation not found");
        PackageAccommodation entity = new PackageAccommodation(
                new PackageAccommodationIds(request.getTourPackage(), request.getAccommodation()),
                request.getNightStay()
        );
        packageAccommodationRepository.save(entity);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(PackageAccommodationRequest request){
        PackageAccommodation entity = new PackageAccommodation(
                new PackageAccommodationIds(request.getTourPackage(), request.getAccommodation()),
                request.getNightStay()
        );
        packageAccommodationRepository.delete(entity);

    }
}
