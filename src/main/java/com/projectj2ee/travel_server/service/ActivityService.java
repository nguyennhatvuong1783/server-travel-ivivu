package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.ActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Activity;
import com.projectj2ee.travel_server.mapper.AccommodationMapper;
import com.projectj2ee.travel_server.mapper.ActivityMapper;
import com.projectj2ee.travel_server.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

    public ApiResponse<List<Activity>> getAllActivity(){
        return new ApiResponse<List<Activity>>(HttpStatus.OK.value(), "Success",activityRepository.findAll());
    }

    public ApiResponse<Activity> getActivityById(String id){
        Activity activity = activityRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Activity not found"));
        return new  ApiResponse<Activity>(HttpStatus.OK.value(), "Success",activity);
    }

    public ApiResponse<Activity> addActivity(ActivityRequest activityRequest){
        Activity entity = new Activity();
        entity = activityMapper.activityRequestToActivity(activityRequest);
        entity.setStatus(true);
        Activity saveEntity = activityRepository.save(entity);
        return new ApiResponse<Activity>(HttpStatus.CREATED.value(), "Created success",saveEntity);
    }

    public ApiResponse<Activity> updateActivity(String id,ActivityRequest activityRequest){
        Activity entity = activityRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Activity not found"));
        entity = activityMapper.activityRequestToActivity(activityRequest);
        entity.setStatus(true);
        entity.setId(Integer.parseInt(id));
        Activity saveEntity = activityRepository.save(entity);
        return new ApiResponse<Activity>(HttpStatus.CREATED.value(), "Created success",saveEntity);
    }

    public ApiResponse<Activity> deleteActivity(String id){
        Activity entity = activityRepository.findById(Long.parseLong(id)).orElseThrow(()->new RuntimeException("Activity not found"));
        entity.setStatus(false);
        activityRepository.save(entity);
        return new ApiResponse<>(HttpStatus.OK.value(),"Deleted success");
    }

}
