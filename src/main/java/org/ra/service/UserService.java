package org.ra.service;

import java.util.Optional;

import org.ra.adapter.UserAdapter;
import org.ra.model.UserModel;
import org.ra.repository.UserRepository;
import org.ra.util.JwtUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAdapter userAdapter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userRetrived = this.getUserByUsername(username);
		JwtUser jwtUser = this.userAdapter.adaptJwtUser(userRetrived);

		return jwtUser;
	}
	
	public UserModel getUser(String id) {
		Optional<UserModel> userRetrieved = this.userRepository.findById(id);
		
		return userRetrieved.isPresent()? userRetrieved.get(): null;
	}
	
	public UserModel getUserByUsername(String username) {
		UserModel userRetrieved = this.userRepository.findByUsername(username);
		
		return userRetrieved;
	}
	
	public UserModel save(UserModel userToSave) throws Exception {
		String password = userToSave.getPassword();
		
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        userToSave.setPassword(hashPassword);
        
        UserModel userInserted = null;
        
        try {
        	userInserted = this.userRepository.save(userToSave);
        	if(userInserted == null) {
        		throw new Exception("error to save user!");
        	}
        }catch (Exception ex) {
        	LOG.error(ex.getMessage(), ex);
        	throw new Exception("error to save user!");
		}
        
		return userInserted;
	}
	
	public boolean existUserWithUsername(String username) {
		UserModel user = this.getUserByUsername(username);
		
		return user != null;
		
	}
}
