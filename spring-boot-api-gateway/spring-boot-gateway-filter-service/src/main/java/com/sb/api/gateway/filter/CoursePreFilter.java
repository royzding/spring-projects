package com.sb.api.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Base64;

@Slf4j
@Component
public class CoursePreFilter extends AbstractGatewayFilterFactory<CoursePreFilter.Config> {

    public CoursePreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        log.info("Inside Student Pre Filter");
        return (exchange, chain) -> {
            log.info("Intercepted request to account");
            
            ServerHttpRequest request = exchange.getRequest();
            String myHeader = request.getHeaders().getFirst("myHeader");
            
            log.info(myHeader + " course:" + config.getCourse());
            
            if(!myHeader.contains("COURSE")) {
                request = request.mutate().path("forward:/fallback/courseFallback").build();            	
            }
                        
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    @Getter
    @Setter
    public static class Config {
        private String course;
    }
}
