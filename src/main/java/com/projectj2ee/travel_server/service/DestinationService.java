package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.DestinationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Destination;
import com.projectj2ee.travel_server.mapper.DestinationMapper;
import com.projectj2ee.travel_server.repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DestinationService {
    @Autowired
    private final DestinationRepository destinationRepository;

    @Autowired
    private DestinationMapper destinationMapper;

    public ApiResponse<List<Destination>> getAllDestination(){
        return new ApiResponse<List<Destination>>(HttpStatus.OK.value(), "Success",destinationRepository.findAll());
    }

    public ApiResponse<Destination> getDestinationById(int id){
        Destination entity = destinationRepository.findById(id).orElseThrow(()->new RuntimeException("Destination not found"));
        return new ApiResponse<Destination>(HttpStatus.OK.value(), "Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Destination> addDestination(DestinationRequest destinationRequest){
        Destination destination = destinationMapper.toDestination(destinationRequest);
        destination.setStatus(true);
        destinationRepository.save(destination);
        return new ApiResponse<Destination>(HttpStatus.CREATED.value(), "Create Success",destination);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Destination> editDestination(int id,DestinationRequest destinationRequest){
        Destination entity = destinationRepository.findById(id).orElseThrow(()->new RuntimeException("Destination not found"));
        entity = destinationMapper.toDestination(destinationRequest);
        entity.setId(id);
        entity.setStatus(true);
        destinationRepository.save(entity);
        return new ApiResponse<Destination>(HttpStatus.OK.value(), "Update Success",entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Destination> deleteDestination(int id){
        Destination entity = destinationRepository.findById(id).orElseThrow(()->new RuntimeException("Destination not found"));
        entity.setStatus(false);
        destinationRepository.save(entity);
        return new ApiResponse<Destination>(HttpStatus.OK.value(), "Delete Success",entity);
    }

}
