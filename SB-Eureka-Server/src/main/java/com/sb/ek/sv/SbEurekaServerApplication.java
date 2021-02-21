package com.sb.ek.sv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SbEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbEurekaServerApplication.class, args);
	}

}
