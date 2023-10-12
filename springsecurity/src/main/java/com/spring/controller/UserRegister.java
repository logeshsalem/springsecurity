package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Customer;
import com.spring.repository.CustomerRepository;

@RestController
public class UserRegister {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer = null;
		ResponseEntity response = null;
		try {
			String hashpwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashpwd);
			savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getId()>0) {
				response = ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Given user details are successfully Registered");
			}
		}catch (Exception e) {
			response = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurs due to "+e.getMessage());
		}
		return response;
	}

}
