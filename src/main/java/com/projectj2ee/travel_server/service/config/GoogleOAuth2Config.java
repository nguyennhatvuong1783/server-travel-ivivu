package com.projectj2ee.travel_server.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectj2ee.travel_server.utils.GoogleClientCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class GoogleOAuth2Config {
    @Bean
    public GoogleClientCredentials googleClientCredentials() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("google.json"), GoogleClientCredentials.class);
    }
}
