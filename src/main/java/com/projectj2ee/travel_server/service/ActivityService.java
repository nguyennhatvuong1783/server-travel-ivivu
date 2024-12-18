package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.ActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Activity;
import com.projectj2ee.travel_server.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    @Autowired
    private final ActivityRepository activityRepository;


    public ApiResponse<List<Activity>> getAllActivity(){
        return new ApiResponse<List<Activity>>(HttpStatus.OK.value(), "Success",activityRepository.findAll());
    }

    public ApiResponse<Activity> getActivityById(int id){
        Activity activity = activityRepository.findById(id).orElseThrow(()->new RuntimeException("Activity not found"));
        return new  ApiResponse<Activity>(HttpStatus.OK.value(), "Success",activity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Activity> addActivity(ActivityRequest activityRequest){
        Activity entity = new Activity();
        entity.setName(activityRequest.getName());
        entity.setDescription(activityRequest.getDescription());
        entity.setLevel(activityRequest.getLevel());
        entity.setDuration(activityRequest.getDuration());
        entity.setStatus(true);
        Activity saveEntity = activityRepository.save(entity);
        return new ApiResponse<Activity>(HttpStatus.CREATED.value(), "Created success",saveEntity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Activity> updateActivity(int id,ActivityRequest activityRequest){
        Activity entity = activityRepository.findById(id).orElseThrow(()->new RuntimeException("Activity not found"));
        entity.setName(activityRequest.getName());
        entity.setDescription(activityRequest.getDescription());
        entity.setLevel(activityRequest.getLevel());
        entity.setDuration(activityRequest.getDuration());
        entity.setStatus(true);
        entity.setId(id);
        Activity saveEntity = activityRepository.save(entity);
        return new ApiResponse<Activity>(HttpStatus.CREATED.value(), "Created success",saveEntity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Activity> deleteActivity(int id){
        Activity entity = activityRepository.findById(id).orElseThrow(()->new RuntimeException("Activity not found"));
        entity.setStatus(false);
        activityRepository.save(entity);
        return new ApiResponse<>(HttpStatus.OK.value(),"Deleted success");
    }

}
