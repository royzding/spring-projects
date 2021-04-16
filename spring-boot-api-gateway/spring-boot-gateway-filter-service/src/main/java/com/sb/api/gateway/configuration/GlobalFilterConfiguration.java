package com.sb.api.gateway.configuration;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalFilterConfiguration {

	@Bean
	public GlobalFilter customFilter() {
	    return new CustomGlobalFilter();
	}

	public class CustomGlobalFilter implements GlobalFilter, Ordered {

	    @Override
	    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	        log.info("custom global filter");
	        return chain.filter(exchange);
	    }

	    @Override
	    public int getOrder() {
	        return -1;
	    }
	}	
	
}
