package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.UserDto;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private  final UserRepository userRepository;

    @Autowired

    private final PasswordEncoder passwordEncoder;

  public ResponseEntity<User> saveUser(UserDto dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(true);
        LocalDateTime localDateTime = LocalDateTime.now();
        entity.setRegistration_date(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        User savedUser = userRepository.save(entity);
        dto.setPassword("******");
        return ResponseEntity.ok(savedUser);
  }
  public boolean findByUsername(String username){
      Optional<User> byUsername = userRepository.findByUsername(username);
      if (byUsername.isPresent()){
          return true;
      }
      return false;
  }

  public ApiResponse<List<User>> getAllUsers(){
      return new ApiResponse<List<User>>(HttpStatus.CREATED.value(),"Success",userRepository.findAll());
  }

  public ApiResponse<User> getUserById(String userId){
      User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
      return new ApiResponse<User>(HttpStatus.OK.value(), "Success",user);
  }

  public ApiResponse<User> updateUser(String userId,UserDto userDto){
      User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
      userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

      LocalDateTime localDateTime = LocalDateTime.now();
      user.setUpdated_at(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));

      user.setPassword(userDto.getPassword());
      user.setEmail(userDto.getEmail());
      user.setAddress(userDto.getAddress());
      user.setFull_name(userDto.getFull_name());
      user.setPhone_number(userDto.getPhone_number());
      user.setDate_of_birth(userDto.getDate_of_birth());

      User saveUser = userRepository.save(user);
      userDto.setUsername(saveUser.getUsername());
      userDto.setPassword("*****");
      return new ApiResponse<User>(HttpStatus.OK.value(), "Update successfull",saveUser);
  }

  public ApiResponse<User> deletedUser(String userId){
      User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
      user.setStatus(false);
      LocalDateTime localDateTime = LocalDateTime.now();
      user.setDeleted_at(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
      return new ApiResponse<User>(HttpStatus.OK.value(), "Deleled success");
  }
}
