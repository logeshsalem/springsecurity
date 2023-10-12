package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Notices {
	
	@GetMapping("/notices")
	public String getNotice() {
		return "notices";
	}

}
