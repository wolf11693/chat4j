package org.ra.service;

import org.ra.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	private static final Logger LOG = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	private UserService userService;
	
	public UserModel registerUser(UserModel userToRegister) throws Exception {
		LOG.info("registerUser - START - userToRegister={}", userToRegister);
		
		String username = userToRegister.getUsername();
		
		if( this.userService.existUserWithUsername(username) ) {
			throw new Exception("error to register user with username: '" + username + "', beacuse username already exists!");
		}
		
		UserModel userRegistered = this.userService.saveUser(userToRegister);
		
		LOG.info("registerUser - END - idUserRegistered={}", userRegistered.getId());
		return userRegistered;
	}
	
}
