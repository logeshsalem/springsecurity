package com.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.security.model.Customer;
import com.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		
		try {
			String hashpwd = passwordEncoder.encode(customer.getPassword());
			customer.setPassword(hashpwd);
			Customer savedCustomer = customerRepository.save(customer);
			
			if(savedCustomer.getId()>0) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("User Details Successfully Registered");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("User Registration Failed");
			}
			
		}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An Exception Occurred: "+e.getMessage());
		}
		
	}

}
