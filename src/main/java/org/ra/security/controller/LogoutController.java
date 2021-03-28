package org.ra.security.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

	private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);
	
	@GetMapping(path = "/invalidate-session")
	public ResponseEntity<?> logout(HttpSession session) {
		LOG.error("logout - START");
		LOG.error(">> GET /invalidate-session");
		
		session.invalidate();
		
		LOG.error("logout - END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
