package com.marketplace.DealerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaServer
public class DealerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealerServiceApplication.class, args);
	}

}
