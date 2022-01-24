package com.sample.microservices.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	  private final String username;
	  private final String password;

	  @Autowired
	  public WebSecurityConfiguration(
	    @Value("${app.eureka-username}") String username,
	    @Value("${app.eureka-password}") String password
	  ) {
	    this.username = username;
	    this.password = password;
	  }
/*
	@Override
	@SuppressWarnings("deprecation")
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication()
		    .passwordEncoder(NoOpPasswordEncoder.getInstance())
		    .withUser(username).password(password)
		    .authorities("USER");
	}
*/	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
    	System.out.println("################ get into discoveryApplication" + WebSecurityConfiguration.class);

		http
			.headers()
			.contentSecurityPolicy("default-src 'self'; 'strict-dynamic'; frame-ancestors 'none'");
		
		http
			.csrf().disable();
/*		
	    http
		    .authorizeRequests()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .httpBasic();
*/
	}

}
