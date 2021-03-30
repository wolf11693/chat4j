package org.ra.controller;

import java.security.Principal;
import java.util.List;

import org.ra.model.UserModel;
import org.ra.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		LOG.info("getAllUsers - START");
		LOG.info(">> GET /user");

		
		List<UserModel> users = this.userService.getAll();
		
		LOG.info("<< [ retrieved n° {} items ]", users.size());
		LOG.info("getAllUsers - END");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/others-users")
	public ResponseEntity<?> getOthersUsers(Principal principal) {
		LOG.info("getOthersUsers - START");
		LOG.info(">> GET /others-users");
		
		String username = principal.getName();
		List<UserModel> othersUsers = this.userService.getOtherUser(username);
		
		LOG.info("<< [ retrieved n° {} items ]", othersUsers.size());
		LOG.info("getOthersUsers - END");
		return new ResponseEntity<>(othersUsers, HttpStatus.OK);
	}
	
}
