package org.ra.security.controller;

import org.ra.adapter.UserAdapter;
import org.ra.dto.UserInputDto;
import org.ra.model.UserModel;
import org.ra.request.RegisterRequest;
import org.ra.response.RegisterUserResponse;
import org.ra.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class RegisterContoller {

	private static final Logger LOG = LoggerFactory.getLogger(RegisterContoller.class);
	
	@Autowired
	private UserAdapter userAdapter;
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws Exception {
		LOG.info("register - START - body={}", registerRequest);
		LOG.info(">> POST /register");
		
		UserInputDto userToRegister = registerRequest.getUserToRegister();
		UserModel userModel = this.userAdapter.adapatUserModel(userToRegister);
		
		UserModel userRegistred = this.registerService.registerUser(userModel);
		
		RegisterUserResponse registerUserResponse = new RegisterUserResponse();
		registerUserResponse.setIdUserRegistered(userRegistred.getId());
		
		LOG.info("<< [ id={} ]", registerUserResponse.getIdUserRegistered());
		LOG.info("register - END");
		return new ResponseEntity<>(registerUserResponse, HttpStatus.OK);
	}
}
