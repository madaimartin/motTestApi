package hu.szintezis.net.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * This application contains a very basic in memory authentication.
	 * Users can access the API after login.
	 */

	public static final String ROLE_USER = "USER";
	public static final String ROLE_ADMIN = "ADMIN";
	
	// Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("password")
			.roles(ROLE_USER)
		.and()
			.withUser("admin")
			.password("admin")
			.roles(ROLE_USER, ROLE_ADMIN)
		;
	}
	
	// Authorization
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
				.authorizeRequests().antMatchers("/api/**")
				.hasAnyRole(ROLE_USER, ROLE_ADMIN).anyRequest().fullyAuthenticated()
			.and()
				.csrf().disable()
		;
			
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	
}
