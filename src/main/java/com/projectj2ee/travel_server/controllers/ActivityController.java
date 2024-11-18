package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.ActivityRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.Activity;
import com.projectj2ee.travel_server.service.ActivityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {
    @Autowired
    private final ActivityService activityService;
    @GetMapping("/")
    public ApiResponse<List<Activity>> getAllActivity(){
        return activityService.getAllActivity();
    }

    @GetMapping("/{id}")
    public ApiResponse<Activity> getActivityById(@PathVariable("id") int id){
        return activityService.getActivityById(id);
    }

    @PostMapping("/create")
    public ApiResponse<Activity> createActivity(@RequestBody @Valid ActivityRequest activityRequest){
        return activityService.addActivity(activityRequest);
    }

    @PutMapping("/{id}")
    public ApiResponse<Activity> updateActivity(@PathVariable("id") int id,@RequestBody @Valid ActivityRequest activityRequest){
        return activityService.updateActivity(id, activityRequest);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Activity> deleteActivity(@PathVariable("id") int id){
        return activityService.deleteActivity(id);
    }

}
