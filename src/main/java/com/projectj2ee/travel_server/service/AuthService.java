package com.projectj2ee.travel_server.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.projectj2ee.travel_server.dto.request.RefreshRequest;
import com.projectj2ee.travel_server.dto.response.ApiResponse;
import com.projectj2ee.travel_server.entity.InvalidatedToken;
import com.projectj2ee.travel_server.repository.InvalidatedRepository;
import com.projectj2ee.travel_server.security.jwt.JwtUtil;
import com.projectj2ee.travel_server.utils.GoogleClientCredentials;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    @Autowired
    private final InvalidatedRepository invalidatedRepository;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    private final GoogleClientCredentials googleClientCredentials;

    public ApiResponse<Void> logOut(String token){
        String username = jwtUtil.extractUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(token,userDetails)){
            String id = jwtUtil.extractClaim(token, Claims::getId);
            Date expiryTime = jwtUtil.extractExpiration(token);
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


    public String generateAuthUrl(String loginType) {
        String state = URLEncoder.encode(loginType, StandardCharsets.UTF_8); // Tùy chọn thêm trạng thái
        return googleClientCredentials.getInstalled().getAuthUri() + "?" +
                "client_id=" + googleClientCredentials.getInstalled().getClientId() +
                "&redirect_uri=" + URLEncoder.encode(googleClientCredentials.getInstalled().getRedirectUris()[1], StandardCharsets.UTF_8) +
                "&response_type=code" +
                "&scope=" + URLEncoder.encode("email profile", StandardCharsets.UTF_8) +
                "&state=" + state;

    }

    public Map<String, Object> authenticateAndFetchProfile(String code, String loginType) throws IOException {
        String accessToken;

        switch (loginType.toLowerCase()){
            case "google" :
                accessToken = new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(), new GsonFactory(),
                        googleClientCredentials.getInstalled().getClientId(),
                        googleClientCredentials.getInstalled().getClientSecret(),
                        code,
                        googleClientCredentials.getInstalled().getRedirectUris()[1]
                        ).execute().getAccessToken();
                WebClient webClient = WebClient.builder()
                        .baseUrl("https://www.googleapis.com/oauth2/v3/userinfo")
                        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .build();

                return webClient.get()
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .block();
        }
        return null;
    }
}
