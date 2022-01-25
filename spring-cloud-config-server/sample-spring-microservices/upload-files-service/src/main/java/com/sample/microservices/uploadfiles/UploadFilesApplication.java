package com.sample.microservices.uploadfiles;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.sample.microservices.uploadfiles.service.UploadFilesService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.uploadfiles","com.sample.microservices.common"})
@EnableDiscoveryClient
@EnableFeignClients

@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/uploadfiles"),
			@Server(url = "http://localhost:8088/uploadfiles") 
		}, 
		info = @Info(title = "uploadfiles-service", 
		version = "v3", 
		description = "Department-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://gigantic-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class UploadFilesApplication implements CommandLineRunner {
	
  @Resource
  UploadFilesService uploadFilesService;

  public static void main(String[] args) {
    SpringApplication.run(UploadFilesApplication.class, args);
  }

  @Override
  public void run(String... arg) throws Exception {

  }

}
