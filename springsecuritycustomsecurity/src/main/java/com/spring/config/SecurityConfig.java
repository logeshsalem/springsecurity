package com.spring.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
	    	.authorizeHttpRequests((requests) -> requests
	    		.requestMatchers("/myAccount", "/myBalance", "/myLoan", "/myCard").authenticated()
	    		.requestMatchers("/notices", "/contact", "/","/register").permitAll())
	    	
	    	.formLogin(Customizer.withDefaults())
	    	.httpBasic(Customizer.withDefaults());
	
		return http.build();
	}
		
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
		
	
	
	/*@Bean
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
	}*/
	
	
	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
