package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.TourGuideRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.TourGuide;
import com.projectj2ee.travel_server.mapper.TourGuideMapper;
import com.projectj2ee.travel_server.repository.TourGuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TourGuideService {
    @Autowired
    private final TourGuideRepository tourGuideRepository;

    @Autowired
    private TourGuideMapper tourGuideMapper;

    public ApiResponse<List<TourGuide>> getAllTourGuide(){
        return new ApiResponse<List<TourGuide>>(HttpStatus.OK.value(), "Success",tourGuideRepository.findAll());
    }

    public ApiResponse<TourGuide> getTourGuideById(String id){
        TourGuide entity = tourGuideRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Tour Guide not found"));
        return new ApiResponse<TourGuide>(HttpStatus.OK.value(), "Success",entity);
    }

    public ApiResponse<TourGuide> addTourGuide(TourGuideRequest tourGuideRequest){
        TourGuide entity = new TourGuide();
        entity = tourGuideMapper.toTourGuide(tourGuideRequest);
        entity.setStatus(true);
        tourGuideRepository.save(entity);
        return new ApiResponse<TourGuide>(HttpStatus.CREATED.value(), "Create Success",entity);
    }

    public ApiResponse<TourGuide> editTourGuide(String id,TourGuideRequest tourGuideRequest){
        TourGuide entity = tourGuideRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Tour Guide not found"));
        entity = tourGuideMapper.toTourGuide(tourGuideRequest);
        entity.setId(Integer.parseInt(id));
        entity.setStatus(true);
        tourGuideRepository.save(entity);
        return new ApiResponse<TourGuide>(HttpStatus.OK.value(), "Update Success",entity);
    }

    public ApiResponse<TourGuide> deleteTourGuide(String id){
        TourGuide entity = tourGuideRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new RuntimeException("Tour Guide not found"));
        entity.setStatus(false);
        tourGuideRepository.save(entity);
        return new ApiResponse<TourGuide>(HttpStatus.OK.value(), "Delete Success",entity);
    }
}