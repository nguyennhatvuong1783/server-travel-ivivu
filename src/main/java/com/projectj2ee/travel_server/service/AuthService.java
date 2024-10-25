package com.projectj2ee.travel_server.service;

import com.projectj2ee.travel_server.dto.request.LogoutRequest;
import com.projectj2ee.travel_server.dto.request.RefreshRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.AuthenticationResponse;
import com.projectj2ee.travel_server.entity.InvalidatedToken;
import com.projectj2ee.travel_server.repository.InvalidatedRepository;
import com.projectj2ee.travel_server.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    @Autowired
    private final InvalidatedRepository invalidatedRepository;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    public ApiResponse<Void> logOut(LogoutRequest logoutRequest){
        String username = jwtUtil.extractUsernameFromToken(logoutRequest.getToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(logoutRequest.getToken(),userDetails)){
            String id = jwtUtil.extractClaim(logoutRequest.getToken(), Claims::getId);
            Date expiryTime = jwtUtil.extractExpiration(logoutRequest.getToken());
            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(id)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedRepository.save(invalidatedToken);
        }else {
            throw new RuntimeException("Token not valid");
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Log out");
    }

    public ApiResponse<String> refreshToken(RefreshRequest request){
        String username = jwtUtil.extractUsernameFromToken(request.getToken());
        String jwt = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(request.getToken(),userDetails)){
            String id = jwtUtil.extractClaim(request.getToken(), Claims::getId);
            Date expiryTime = jwtUtil.extractExpiration(request.getToken());
            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(id)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedRepository.save(invalidatedToken);

            jwt = jwtUtil.generateToken(userDetails);
        }else {
            throw new RuntimeException("Token not valid");
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Success",jwt);
    }
}
