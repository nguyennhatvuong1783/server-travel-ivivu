package com.projectj2ee.travel_server.security;

import com.projectj2ee.travel_server.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class UserDetailsServiceConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            Optional<com.projectj2ee.travel_server.entity.User> user = userRepository.findByUsername(username);
            if (!user.isPresent()){
                throw  new UsernameNotFoundException("User not found");
            }
            return new User(user.get().getUsername(),user.get().getPassword(), new ArrayList<>());
        };
    }
}
