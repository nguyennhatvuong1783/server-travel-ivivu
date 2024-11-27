package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.Ids.PackageVehicleIds;
import com.projectj2ee.travel_server.dto.request.PackageVehicleRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.PackageVehicle;
import com.projectj2ee.travel_server.repository.PackageVehicleRepository;
import com.projectj2ee.travel_server.repository.TourPackageRepository;
import com.projectj2ee.travel_server.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageVehicleService {
    private final TourPackageRepository tourPackageRepository;

    private final VehicleRepository vehicleRepository;

    private final PackageVehicleRepository packageVehicleRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> add(PackageVehicleRequest request){
        if (!tourPackageRepository.existsById(request.getTourPackage())){
            throw new RuntimeException("Tour package not found");
        }

        if (!vehicleRepository.existsById(request.getVehicle())){
            throw new RuntimeException("Vehicle not found");
        }
        PackageVehicle packageVehicle = new PackageVehicle(new PackageVehicleIds(request.getTourPackage(), request.getVehicle()));
        packageVehicleRepository.save(packageVehicle);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Success");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(PackageVehicleRequest request){
        PackageVehicle entity = new PackageVehicle(new PackageVehicleIds(request.getTourPackage(), request.getVehicle()));
        packageVehicleRepository.delete(entity);
    }

}
