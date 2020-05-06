package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableCircuitBreaker //开启熔断器
//@SpringBootApplication
@EnableHystrixDashboard
@EnableFeignClients //开启feign客户端
@SpringCloudApplication
public class ApplicationConsumerFeign8880 {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConsumerFeign8880.class, args);
	}

}
