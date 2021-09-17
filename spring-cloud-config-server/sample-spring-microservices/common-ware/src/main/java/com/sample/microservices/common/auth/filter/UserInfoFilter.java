package com.sample.microservices.common.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.common.model.UserInfoStore;


public class UserInfoFilter extends OncePerRequestFilter {

    private static final String USER_INFO_HEADER = "X-USER-INFO";

	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoFilter.class);

	@Autowired
	private UserInfoStore userInfoStore;

    @Autowired
    private ObjectMapper mapper;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String userInfoHeader = request.getHeader(USER_INFO_HEADER);
		
		try {
			if(userInfoHeader != null) {
				UserInfoStore headerUserInfo = mapper.readValue(userInfoHeader, UserInfoStore.class);
				this.userInfoStore.copyData(headerUserInfo);
				LOGGER.info("Loggged in User {} ", this.userInfoStore);
			}

			filterChain.doFilter(request, response);
			
		} finally {
		    // Otherwise when a previously used container thread is used, it will have the old tenant id set and
		    // if for some reason this filter is skipped, userInfoStore will hold an unreliable value
		    this.userInfoStore.clear();
		}
		
	}

}