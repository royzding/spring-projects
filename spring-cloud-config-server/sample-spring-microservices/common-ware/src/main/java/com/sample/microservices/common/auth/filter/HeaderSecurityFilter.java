package com.sample.microservices.common.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

public class HeaderSecurityFilter extends OncePerRequestFilter {
	
	private static final String HEADER_SVC_KEY = "X-SVC-KEY";

	private String svcKey;
	
	public HeaderSecurityFilter(String svcKey) {
		this.svcKey = svcKey;
	}
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
    	
    	String svcHeader = request.getHeader(HEADER_SVC_KEY);
/*    	
    	System.out.println("====svcKey=========" + svcKey);
    	System.out.println("=============" + request.getRequestURI());
    	System.out.println("=====svcHeader========" + svcHeader);
    	 
        if(svcHeader == null || !svcHeader.equals(this.svcKey)) {
        	throw new BadCredentialsException("The Service Key was not found!");
        }
*/
        filterChain.doFilter(request, response);

    }
}