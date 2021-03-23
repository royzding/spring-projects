package com.sb.ek.fn.clt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class SbEurekaClientFeignApplication {

	@Autowired
	private GreetingClient greetingClient;
	
	public static void main(String[] args) {
		SpringApplication.run(SbEurekaClientFeignApplication.class, args);
	}

	@RequestMapping("/greeting")
	public String greeting() {
		return this.greetingClient.greeting() + " greeting from feign!";
	}
}

@FeignClient(name="spring-cloud-eureka-client")
interface GreetingClient {
	@RequestMapping("/greeting")
	String greeting();
}