package com.sample.microservices.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuth2AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
    }

}


//To check the results, use the tool https://oidcdebugger.com/
//Authorize URI: http://auth-server:9000/oauth2/authorize
//Client ID: articles-client