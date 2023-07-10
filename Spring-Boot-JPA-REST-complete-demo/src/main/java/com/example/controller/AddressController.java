package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address/")
public class AddressController {
	
	Logger logger = LoggerFactory.getLogger(AddressController.class);

	@GetMapping("getAddress")
	public String getAddress() {
		
		logger.error("Inside Error");
		logger.warn("Inside Warning");
		logger.info("Inside Info");
		logger.debug("Inside Debug");
		logger.trace("Inside Trace");
		
		return "This is address of student";
	}
}
