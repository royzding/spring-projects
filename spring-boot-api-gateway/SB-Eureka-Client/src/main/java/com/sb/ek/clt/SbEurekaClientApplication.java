package com.sb.ek.clt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@RestController
public class SbEurekaClientApplication implements GreetingController {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Value("${spring.application.name}")
	private String appName;
	
	public static void main(String[] args) {
		SpringApplication.run(SbEurekaClientApplication.class, args);
	}

	@Override
	public String greeting() {
		return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
	}

}

interface GreetingController {
	@RequestMapping("/greeting")
	String greeting();
}
