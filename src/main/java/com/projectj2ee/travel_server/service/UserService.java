package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.enums.Role;
import com.projectj2ee.travel_server.dto.request.LogoutRequest;
import com.projectj2ee.travel_server.dto.request.UserDto;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.PageResponse;
import com.projectj2ee.travel_server.entity.InvalidatedToken;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.repository.InvalidatedRepository;
import com.projectj2ee.travel_server.repository.UserRepository;
import com.projectj2ee.travel_server.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


  public ResponseEntity<User> saveUser(UserDto dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        entity.setRoles(roles);

        entity.setStatus(true);
        User savedUser = userRepository.save(entity);
        return ResponseEntity.ok(savedUser);
  }
  public boolean findByUsername(String username){
      Optional<User> byUsername = userRepository.findByUsername(username);
      if (byUsername.isPresent()){
          return true;
      }
      return false;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  public PageResponse<User> getAllUsers(int page, int size) {
      Sort sort = Sort.by("registration_date").descending();
      Pageable pageable = PageRequest.of(page - 1, size, sort);
      var pageData = userRepository.findAll(pageable);

      return PageResponse.<User>builder()
              .statusCode(HttpStatus.OK.value())
              .currentPage(page)
              .pageSize(pageData.getSize())
              .totalPages(pageData.getTotalPages())
              .data(pageData.getContent().stream().toList()).build();
  }

  @PostAuthorize("returnObject.data.username == authentication.name")
  public ApiResponse<User> getUserById(String userId){
      User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
      return new ApiResponse<User>(HttpStatus.OK.value(), "Success",user);
  }

  public ApiResponse<User> getMyInfo(){
      var context = SecurityContextHolder.getContext();
      String username = context.getAuthentication().getName();
      User user = userRepository.findByUsername(username)
              .orElseThrow(()->new RuntimeException("User not existed"));
      return new ApiResponse<User>(HttpStatus.OK.value(), "Success",user);
  }

  @PostAuthorize("returnObject.data.username == authentication.name")
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

  @PreAuthorize("hasAuthority('ADMIN')")
  public ApiResponse<User> deletedUser(String userId){
      User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
      user.setStatus(false);
      LocalDateTime localDateTime = LocalDateTime.now();
      user.setDeleted_at(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
      return new ApiResponse<User>(HttpStatus.OK.value(), "Deleted success");
  }


}
