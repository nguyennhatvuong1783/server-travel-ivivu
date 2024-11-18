package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.VehicleRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Vehicle;
import com.projectj2ee.travel_server.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("/")
    public ApiResponse<List<Vehicle>> getAllVehicle(){
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public ApiResponse<Vehicle> getVehicleById(@PathVariable("id") int id){
        return vehicleService.getVehicleById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Vehicle> createVehicle(@RequestBody VehicleRequest vehicleRequest){
        return vehicleService.addVehicle(vehicleRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Vehicle> updateVehicle(@PathVariable("id") int id, @RequestBody VehicleRequest vehicleRequest){
        return vehicleService.updateVehicle(vehicleRequest,id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Vehicle> deleteVehicle(@PathVariable("id") int id){
        return vehicleService.deleteVehicle(id);
    }
}
