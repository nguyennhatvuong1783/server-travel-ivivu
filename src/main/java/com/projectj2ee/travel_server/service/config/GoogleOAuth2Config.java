package com.projectj2ee.travel_server.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectj2ee.travel_server.utils.GoogleClientCredentials;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@AllArgsConstructor
public class GoogleOAuth2Config {
    @Bean
    public GoogleClientCredentials googleClientCredentials() throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "google.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new FileInputStream(filePath.toString()), GoogleClientCredentials.class);
    }


}
