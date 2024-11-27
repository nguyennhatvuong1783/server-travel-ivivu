package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.PackageDestinationIds;
import com.projectj2ee.travel_server.dto.request.PackageDestinationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PackageDestination;
import com.projectj2ee.travel_server.repository.DestinationRepository;
import com.projectj2ee.travel_server.repository.PackageDestinationRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageDestinationService {
    private final TourPackageRepository tourPackageRepository;

    private final DestinationRepository destinationRepository;

    private final PackageDestinationRepository packageDestinationRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> add(PackageDestinationRequest request){
        if (!tourPackageRepository.existsById(request.getTourPackage())) throw new RuntimeException("Tour package not found");
        if (!destinationRepository.existsById(request.getDestination())) throw new RuntimeException("Destination not found");

        PackageDestination entity = new PackageDestination(
                new PackageDestinationIds(request.getTourPackage(), request.getDestination()),
                request.getVisitOrder());
        packageDestinationRepository.save(entity);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(PackageDestinationRequest request){
        PackageDestination entity = new PackageDestination(
                new PackageDestinationIds(request.getTourPackage(), request.getDestination()),
                request.getVisitOrder());
        packageDestinationRepository.delete(entity);
    }
}
