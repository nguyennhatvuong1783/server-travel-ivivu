package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.UserDto;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.exceptions.InvalidPayloadException;
import com.projectj2ee.travel_server.exceptions.UserIdAlreadyExistException;
import com.projectj2ee.travel_server.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDto userDto){
        if (Objects.isNull(userDto)){
            throw new InvalidPayloadException("Payload cannot be null");
        }
        if (userService.findByUsername(userDto.getUsername())){
            throw new UserIdAlreadyExistException("Username is already taken");
        }
        return userService.saveUser(userDto);
    }

    @GetMapping("/users")
    public ApiResponse<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{Id}")
    public ApiResponse<User> getUserById(@PathVariable("Id") String Id){
        return userService.getUserById(Id);
    }

    @PutMapping("/user/{Id}")
    public ApiResponse<User> updateUser(@PathVariable("Id") String Id, @RequestBody UserDto userDto){
        if (Objects.isNull(userDto)){
            throw new InvalidPayloadException("Payload cannot be null");
        }
        return userService.updateUser(Id,userDto);
    }

    @DeleteMapping("/user/{Id}")
    public ResponseEntity<Void> deletedUser(@PathVariable("Id") String Id){
        userService.deletedUser(Id);
        return ResponseEntity.noContent().build();
    }


}
