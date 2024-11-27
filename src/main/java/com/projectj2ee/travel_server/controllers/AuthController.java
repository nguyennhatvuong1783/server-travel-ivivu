package com.projectj2ee.travel_server.controllers;

import com.projectj2ee.travel_server.dto.request.AuthenticationRequest;
import com.projectj2ee.travel_server.dto.request.RefreshRequest;
import com.projectj2ee.travel_server.dto.request.UserDto;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.dto.response.AuthenticationResponse;
import com.projectj2ee.travel_server.security.jwt.JwtUtil;
import com.projectj2ee.travel_server.service.AuthService;
import com.projectj2ee.travel_server.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

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

    private final UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class.getName());


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                                            @Context HttpServletResponse response) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException badCredentialsException){
            throw new RuntimeException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("Token",jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/social-login")
    public ResponseEntity<String> socialAuth(
            @RequestParam("login-type") String loginType,
            HttpServletRequest request
    ){
        loginType = loginType.trim().toLowerCase();
        String url = authService.generateAuthUrl(loginType);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/social/callback")
    public ApiResponse<String> callback(
            @RequestParam("code") String code,
            @RequestParam("login-type") String loginType,
            HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws IOException {
        Map<String, Object> userInfo = authService.authenticateAndFetchProfile(code, loginType);

        if (userInfo == null)
        {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Failed to authenticate");
        }

        String username ="";
        String email = "";
        String fullName = "";
        if (loginType.trim().equals("google")){
            username = (String) Objects.requireNonNull(userInfo.get("sub"), "");
            fullName = (String) Objects.requireNonNull(userInfo.get("name"), "");
            email = (String) Objects.requireNonNull(userInfo.get("email"), "");
        }
        if (!userService.findByUsername(username)){
            UserDto userDto = new UserDto();
            userDto.setEmail(email);
            userDto.setUsername(username);
            userDto.setFull_name(fullName);
            userDto.setPassword("1234");
            userService.saveUser(userDto);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("Token",jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return new ApiResponse<>(HttpStatus.OK.value(), "Success");
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logOut(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("Token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        if (token != null){
            return authService.logOut(token);
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Token invalid");
    }

    @PostMapping("/refresh")
    public ApiResponse<String> refreshToken(@RequestBody RefreshRequest request){
        return authService.refreshToken(request);
    }


}
