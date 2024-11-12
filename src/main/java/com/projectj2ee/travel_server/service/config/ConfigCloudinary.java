package com.projectj2ee.travel_server.service.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary configKey(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dhis8yzem");
        config.put("api_key", "133792976766817");
        config.put("api_secret", "NLBSR18TL9JMYvHreSXTMOfYUdk");
        return new Cloudinary(config);
    }

}
