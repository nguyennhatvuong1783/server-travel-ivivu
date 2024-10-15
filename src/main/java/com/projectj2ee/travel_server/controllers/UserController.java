package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.UserDto;
import com.projectj2ee.travel_server.exceptions.InvalidPayloadException;
import com.projectj2ee.travel_server.exceptions.UserIdAlreadyExistException;
import com.projectj2ee.travel_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        if (Objects.isNull(userDto)){
            throw new InvalidPayloadException("Payload cannot be null");
        }
        if (userService.findByUsername(userDto.getUsername())){
            throw new UserIdAlreadyExistException("Username is already taken");
        }
        return userService.saveUser(userDto);
    }
}
