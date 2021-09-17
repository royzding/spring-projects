package com.sample.microservices.gateway.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AuthorizationFilterUtil {
	
	private AuthorizationFilterUtil() {}
	
    public static boolean isAuthorizedAccess(String groups, String authorizationHeader) {
    	
        boolean isValid = true;

        // Logic for checking the value

        return isValid;
    }

	public static Mono<Void> unauthorizedAccess(ServerWebExchange exchange, String cause) {
		
		addSecureHeaders(exchange.getResponse().getHeaders());
		
		//set UNAUTHORIZED 401 response and stop the processing
		
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		byte[] bytes = cause.getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
		
		return exchange.getResponse().writeWith(Flux.just(buffer));
	}
	
	public static void addSecureHeaders(HttpHeaders headers) {
		
		//set secure response headers
		headers.add("X-XSS-Protection", "1; mode=block");
		headers.add("X-Content-Type-Options", "nosniff");
		headers.add("X-Frame-Options", "DENY");
		headers.add("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
		headers.add("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Content-Security-Policy", "script-src 'self' 'strict-dynamic' https://trustedscripts.example.com; report-uri /csp-report-endpoint/");

	}

}
