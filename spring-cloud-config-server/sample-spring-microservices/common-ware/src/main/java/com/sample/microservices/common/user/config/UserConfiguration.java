package com.sample.microservices.common.user.config;

import java.util.Arrays;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.sample.microservices.common.auth.filter.UserInfoFilter;
import com.sample.microservices.common.model.UserInfoStore;

@Configuration
@Profile("user-info")
public class UserConfiguration {

	@Bean
	public UserInfoFilter userInfoFilter() {
		return new UserInfoFilter();
	}

	@Bean
	public FilterRegistrationBean<UserInfoFilter> userInfoFilterRegistration() {
		FilterRegistrationBean<UserInfoFilter> result = new FilterRegistrationBean<>();
		result.setFilter(this.userInfoFilter());
		result.setUrlPatterns(Arrays.asList("/*"));
		result.setName("User Info Store Filter");
		result.setOrder(1);
		return result;
	}

	@Bean(destroyMethod = "destroy")
	public ThreadLocalTargetSource threadLocalUserInfoStore() {
		ThreadLocalTargetSource result = new ThreadLocalTargetSource();
		result.setTargetBeanName("userInfoStore");
		return result;
	}

	@Primary
	@Bean(name = "proxiedThreadLocalTargetSource")
	public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
		ProxyFactoryBean result = new ProxyFactoryBean();
		result.setTargetSource(threadLocalTargetSource);
		return result;
	}

	@Bean(name = "userInfoStore")
	@Scope(scopeName = "prototype")
	public UserInfoStore userInfoStore() {
		return new UserInfoStore();
	}

}