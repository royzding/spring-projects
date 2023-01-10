package com.sample.microservices.gateway.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile("dev")
@Slf4j
public class SecurityWebFilter implements WebFilter {
		
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		log.info("Security WebFilter");
		
		HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
		
		HttpHeaders responseHeaders = exchange.getResponse().getHeaders();
		
		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "*");
		responseHeaders.add("Access-Control-Request-Method", "*");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Request-Headers", "*");
		responseHeaders.add("Access-Control-Allow-Credentials", "true");
		responseHeaders.add("Access-Control-Max-Age", "2400");
		

		return chain.filter(exchange);
	}
    
}