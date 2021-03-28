package org.ra.service;

import org.ra.model.ChatRoomUserModel;
import org.ra.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	private static final Logger LOG = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatRoomUserService chatRoomUserService;
	
	public UserModel registerUser(UserModel userToRegister) throws Exception {
		LOG.info("registerUser - START - userToRegister={}", userToRegister);
		
		String username = userToRegister.getUsername();
		
		if( !this.canRegisterUser(username) ) {
			throw new Exception("error to register user with username: '" + username + "', beacuse username already exists!");
		}
		
		UserModel userRegistered = this.userService.save(userToRegister);
		
		ChatRoomUserModel chatRoomUserCeated = this.chatRoomUserService.createChatRoomUser(userRegistered);
		
		LOG.info("registerUser - END - idUserRegistered={}", userRegistered.getId());
		return userRegistered;
	}
	
	
	public boolean canRegisterUser(String username) {
		// verifico che l'username dell'utente da registrare non esista già
		if( this.userService.existUserWithUsername(username) ) {
			return false;
		}
		
		return true;
	}
	
}
