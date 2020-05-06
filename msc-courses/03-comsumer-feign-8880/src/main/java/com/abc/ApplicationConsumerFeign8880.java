package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //开启feign客户端
@SpringBootApplication
public class ApplicationConsumerFeign8880 {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConsumerFeign8880.class, args);
	}

}
