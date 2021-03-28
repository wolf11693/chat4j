package org.ra.adapter;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.ra.dto.UserInputDto;
import org.ra.model.UserModel;
import org.ra.util.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {

	public UserModel adapatUserModel(UserInputDto userInputDto) {
		UserModel userModel = new UserModel();
		
		userModel.setUsername(userInputDto.getUsername());
		userModel.setFirstname(userInputDto.getFirstname());
		userModel.setLastname(userInputDto.getLastname());
		userModel.setEmail(userInputDto.getEmail());
		userModel.setPassword(userInputDto.getPassword());
		userModel.setImage(userInputDto.getImage());
		
		return userModel;
	}
	
	public JwtUser adaptJwtUser(UserModel userModel) {
		long idUser = new ObjectId(userModel.getId()).getTimestamp();
		
		JwtUser jwtUser = new JwtUser( idUser, userModel.getUsername(),
									   userModel.getFirstname(), userModel.getLastname(),
									   userModel.getEmail(), userModel.getPassword(),
									   new ArrayList<GrantedAuthority>(), true, 
									   null );

		return jwtUser;
	}
	
}
