package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.VehicleRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Vehicle;
import com.projectj2ee.travel_server.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public ApiResponse<List<Vehicle>> getAllVehicle(){
        return new ApiResponse<List<Vehicle>>(HttpStatus.OK.value(), "Success",vehicleRepository.findAll());
    }

    public ApiResponse<Vehicle> getVehicleById(int id){
        Vehicle entity = vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("Vehicle not found"));
        return new ApiResponse<Vehicle>(HttpStatus.OK.value(), "Success",entity);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Vehicle> addVehicle(VehicleRequest vehicleRequest){
        Vehicle entity = new Vehicle();
        entity.setName(vehicleRequest.getVehicle_name());
        entity.setDescription(vehicleRequest.getDescription());
        entity.setStatus(true);
        Vehicle saveEntity = vehicleRepository.save(entity);
        return new ApiResponse<Vehicle>(HttpStatus.CREATED.value(), "Create success",saveEntity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Vehicle> updateVehicle(VehicleRequest vehicleRequest,int id){
        Vehicle entity = vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("Vehicle not found"));
        entity.setName(vehicleRequest.getVehicle_name());
        entity.setDescription(vehicleRequest.getDescription());
        entity.setStatus(true);
        Vehicle saveEntity = vehicleRepository.save(entity);
        return new ApiResponse<Vehicle>(HttpStatus.OK.value(), "Update success",saveEntity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Vehicle> deleteVehicle(int id){
        Vehicle entity = vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("Vehicle not found"));
        entity.setStatus(false);
        vehicleRepository.save(entity);
        return new ApiResponse<Vehicle>(HttpStatus.OK.value(), "Deleted success");
    }
}
