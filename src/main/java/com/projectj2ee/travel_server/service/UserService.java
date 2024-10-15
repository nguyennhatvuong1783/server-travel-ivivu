package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.UserDto;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public ResponseEntity<UserDto> saveUser(UserDto dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        User savedUser = userRepository.save(entity);
        dto.setPassword("******");
        dto.setUser_id(savedUser.getUser_id());
        return ResponseEntity.ok(dto);
  }
  public boolean findByUsername(String username){
      Optional<User> byUsername = userRepository.findByUsername(username);
      if (byUsername.isPresent()){
          return true;
      }
      return false;
  }
}
