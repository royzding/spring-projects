package com.sample.microservices.gateway;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import io.netty.handler.codec.http.HttpMethod;

@SpringBootApplication(exclude= {ReactiveUserDetailsServiceAutoConfiguration.class},
	scanBasePackages= {"com.sample.microservices.gateway","com.sample.microservices.common"})
@EnableDiscoveryClient
@EnableWebFluxSecurity
public class GatewayApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Autowired
	RouteDefinitionLocator locator;

	//http://localhost:8889/swagger-ui.html
	//https://piotrminkowski.com/2020/02/20/microservices-api-documentation-with-springdoc-openapi/
	@Bean
	public List<GroupedOpenApi> apis() {
	   List<GroupedOpenApi> groups = new ArrayList<>();
	   List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
	   definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
	      String name = routeDefinition.getId().replaceAll("-service", "");
	      groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build());
	   });
	   return groups;
	}	

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
		return httpSecurity
				.csrf()
				.requireCsrfProtectionMatcher(new NegatedServerWebExchangeMatcher(
						new PathPatternParserServerWebExchangeMatcher("/v3/**")))
				.disable()
				.headers()
				.contentSecurityPolicy("default-src 'self'; 'strict-dynamic'; frame-ancestors 'none'; script-src 'self' 'unsafe-inline'; ")
				.and()
				.and().build();
		
	}
}
