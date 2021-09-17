package com.sample.microservices.gateway.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.gateway.filter.AuthorizationFilterUtil;

import reactor.core.publisher.Mono;

public class ApiGatewayConfiguration {

    final Logger logger =  LoggerFactory.getLogger(ApiGatewayConfiguration.class);
    
    @Value("${auth.check-url}")
    private boolean checkUrl;

    @Value("${auth.token.header-url}")
    private String headerUrl;

    @Value("${auth.token.header-url-value}")
    private String headerUrlValue;

    @Bean
    public ObjectMapper objectMapper() {
    	return new ObjectMapper();
    }

	@Bean
	public GlobalFilter customFilter() {
		return new CustomFilter();
	}
    
	
	public class CustomFilter implements GlobalFilter, Ordered {


		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			
	        logger.info("Custom Global Filter executed");

	        final ServerHttpRequest request = exchange.getRequest();
	        
	        if(checkUrl) {
	        	final HttpHeaders headers = request.getHeaders();
	        	final String url = headers.getFirst(headerUrl);
	        	
	        	if(url == null || !url.startsWith(headerUrlValue)) {
	        		
	        		String cause = "Access unauthorized: url headers not right!";
	        		
	        		logger.error("{} {} {}", cause, " [URI]:", request.getURI());
	        		
	        		return AuthorizationFilterUtil.unauthorizedAccess(exchange,cause);	        		
	        		
	        	}
	        }
			
	        
	        return chain.filter(exchange);
		}
		
		@Override
		public int getOrder() {

			return -1;
		}
		
	
	}
		
}
