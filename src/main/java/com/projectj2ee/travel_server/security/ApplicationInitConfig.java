package com.projectj2ee.travel_server.security;

import com.projectj2ee.travel_server.dto.enums.Role;
import com.projectj2ee.travel_server.entity.User;
import com.projectj2ee.travel_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .roles(roles)
                        .status(true)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created");
            }
        };

    }
}
