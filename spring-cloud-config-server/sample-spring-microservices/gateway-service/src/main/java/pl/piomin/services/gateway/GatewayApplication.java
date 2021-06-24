package pl.piomin.services.gateway;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
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
	      groups.add(GroupedOpenApi.builder().pathsToMatch("/").setGroup(name).build());
	   });
	   return groups;
	}	

}
