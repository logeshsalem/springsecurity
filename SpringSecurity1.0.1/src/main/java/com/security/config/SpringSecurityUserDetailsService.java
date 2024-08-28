package com.security.config;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.security.model.Customer;
import com.security.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpringSecurityUserDetailsService implements UserDetailsService {
	
	//custom username password checker
	private final CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer =  customerRepository.findByEmail(username)
		.orElseThrow(()-> new UsernameNotFoundException("UserName Not Found "+username));
		
		List<GrantedAuthority> authorities = customer.getAuthorities().stream()
		.map(authority->new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
		
		
		return new User(customer.getEmail(), customer.getPassword(), authorities);
	}

}
