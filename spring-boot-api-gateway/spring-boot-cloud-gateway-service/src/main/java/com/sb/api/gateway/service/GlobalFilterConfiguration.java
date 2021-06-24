package com.sb.api.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalFilterConfiguration {
	
	@Autowired
	RouteDefinitionLocator locator;

	//https://piotrminkowski.com/2020/02/20/microservices-api-documentation-with-springdoc-openapi/
	@Bean
	public List<GroupedOpenApi> apis() {
	   List<GroupedOpenApi> groups = new ArrayList<>();
	   List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
	   definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
	      String name = routeDefinition.getId().replaceAll("-service", "");
	      groups.add(GroupedOpenApi.builder().pathsToMatch("/").setGroup(name).build());
	   });
	   return groups;
	}	
	

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
