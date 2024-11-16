package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.AuthenticationRequest;
import com.projectj2ee.travel_server.dto.request.LogoutRequest;
import com.projectj2ee.travel_server.dto.request.RefreshRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.AuthenticationResponse;
import com.projectj2ee.travel_server.security.jwt.JwtUtil;
import com.projectj2ee.travel_server.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    private final AuthService authService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class.getName());


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                                            @Context HttpServletResponse response) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException badCredentialsException){
            LOG.error("Incorrect username or password");
            throw new RuntimeException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("Token",jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @PostMapping("/logout")
    public ApiResponse<Void> logOut(@RequestBody LogoutRequest logoutRequest){
        return authService.logOut(logoutRequest);
    }

    @PostMapping("/refresh")
    public ApiResponse<String> refreshToken(@RequestBody RefreshRequest request){
        return authService.refreshToken(request);
    }


}
