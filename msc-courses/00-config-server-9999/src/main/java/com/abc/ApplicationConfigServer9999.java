package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ApplicationConfigServer9999 {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfigServer9999.class, args);
	}

}
