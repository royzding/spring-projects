package com.sb.api.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.api.gateway.filter.AccountPostFilter;
import com.sb.api.gateway.filter.AccountPreFilter;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }

    @Bean
    public RouteLocator restaurantRoute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p
                        .path("/digi/retailbank/v1/**")
                        .filters(f -> f.hystrix(config -> {
                            config
                                    .setName("retail-account")
                                    .setFallbackUri("forward:/fallback/accountFallback");
                        })
                                .filter(new AccountPreFilter().apply(new AccountPreFilter.Config()), 0)
                                .filter(new AccountPostFilter().apply(new AccountPostFilter.Config()), 1))
                        .uri("lb://RETAIL-BANKING"))
                .build();
    }

}
