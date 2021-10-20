package com.sample.microservices.discovery;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
    	System.out.println("################ get into discoveryApplication" + WebSecurityConfiguration.class);

		http
		.headers()
		.contentSecurityPolicy("default-src 'self'; 'strict-dynamic'; frame-ancestors 'none'");
		
		http
		.csrf().disable();
	}

}