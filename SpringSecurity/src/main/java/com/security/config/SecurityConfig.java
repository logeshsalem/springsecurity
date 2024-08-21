package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import com.security.exceptionhandling.CustomBasicAuthenticationPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests)->requests.anyRequest().denyAll());
//		http.authorizeHttpRequests((requests)->requests.anyRequest().permitAll());
		http.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
			.csrf(csrfConfig -> csrfConfig.disable())
			.authorizeHttpRequests((requests)->requests
				.requestMatchers("/myBalance","/myAccount", "/myCards","/myLoans").authenticated()
				.requestMatchers("/notices", "/contact", "/error","/register").permitAll()
				);
		
		http.formLogin(withDefaults());
		http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationPoint()));
		
		return http.build();
	}
	
	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	*/
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
	
	/*@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User
				.withUsername("users")
				.password("{noop}Logesh7871!")
				.authorities("user")
				.build();
		
		UserDetails admin = User
				.withUsername("admin")
				.password("{bcrypt}$2a$12$s2QAUxackhrXU4W/c2HGx.SUiTj/bS.xqWc7ln/zEuBgEBwuyYufC")
				.authorities("admin")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/

}
