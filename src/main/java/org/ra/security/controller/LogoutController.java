package org.ra.security.controller;

import java.security.Principal;

import org.ra.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

	private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping(path = "/invalidate-session")
	public ResponseEntity<?> logout(Principal principal) {
		LOG.error("logout - START");
		LOG.error(">> GET /invalidate-session");
		
		String username = principal.getName();
		this.authenticationService.shutdownAuthenticationUser(username);
		
		LOG.error("logout - END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
