package pl.piomin.services.employee.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${auth.svcKey}")
	private String svcKey;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(new HeaderSecurityFilter(svcKey), BasicAuthenticationFilter.class)
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/**").permitAll();
	}

}