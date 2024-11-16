package com.projectj2ee.travel_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ServerTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTravelApplication.class, args);
	}

}
