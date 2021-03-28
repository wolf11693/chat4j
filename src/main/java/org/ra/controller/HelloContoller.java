package org.ra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloContoller {

	private static final Logger LOG = LoggerFactory.getLogger(HelloContoller.class);
		
	@GetMapping
	public ResponseEntity<?> hello() {
		LOG.info("register - START");
		LOG.info(">> GET /hello");
		
		String message = "hello world!";
		
		LOG.info("hello - END");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
