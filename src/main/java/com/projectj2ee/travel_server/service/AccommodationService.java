package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.AccommodationRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Accommodation;
import com.projectj2ee.travel_server.mapper.AccommodationMapper;
import com.projectj2ee.travel_server.repository.AccommodationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccommodationService {
    @Autowired
    private final AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationMapper accommodationMapper;

    public ApiResponse<List<Accommodation>> getAllAccommodation(){
        return new ApiResponse<List<Accommodation>>(HttpStatus.OK.value(),"Success",accommodationRepository.findAll());
    }

    public ApiResponse<Accommodation> getAccommodationById(String id){
        Accommodation entity = accommodationRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Accommondation not found"));
        return new ApiResponse<Accommodation>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<Accommodation> addAccommodation(AccommodationRequest accommodationRequest){
        Accommodation entity = new Accommodation();
        entity = accommodationMapper.accommodationRequestToAccommodation(accommodationRequest);
        entity.setStatus(true);
        accommodationRepository.save(entity);
        return new ApiResponse<Accommodation>(HttpStatus.CREATED.value(), "Created success",entity);
    }

    public ApiResponse<Accommodation> updateAccommodation(String id, AccommodationRequest accommodationRequest){
        Accommodation entity = accommodationRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Accommondation not found"));
        entity = accommodationMapper.accommodationRequestToAccommodation(accommodationRequest);
        entity.setStatus(true);
        entity.setId(Integer.parseInt(id));
        accommodationRepository.save(entity);
        return new ApiResponse<Accommodation>(HttpStatus.OK.value(), "Updated success",entity);
    }

    public ApiResponse<Accommodation> deletedAccommodation(String id){
        Accommodation entity = accommodationRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Accommondation not found"));
        entity.setStatus(false);
        accommodationRepository.save(entity);
        return new ApiResponse<Accommodation>(HttpStatus.OK.value(), "Deleted success",entity);
    }
}
