package com.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.requestMatchers("/myAccount","myBalance", "myLoad", "myCards").authenticated()
			.requestMatchers("/notice","/contact","/").permitAll()
		.and().formLogin()
		.and().httpBasic();		
		return http.build();
		
//		http.authorizeHttpRequests()
//			.anyRequest().denyAll()
//			.and().formLogin()
//			.and().httpBasic();
//		return http.build();
		
//		http.authorizeHttpRequests()
//		.anyRequest().permitAll()
//		.and().formLogin()
//		.and().httpBasic();
//		return http.build();		
		
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.authorities("admin")				
				.build();
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.authorities("read")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
