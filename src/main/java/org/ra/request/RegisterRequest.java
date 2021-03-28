package org.ra.request;

import org.ra.dto.UserInputDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

	@JsonProperty(value = "user")
	private UserInputDto userToRegister;
	
	public RegisterRequest() {
		super();
	}

	public UserInputDto getUserToRegister() {
		return userToRegister;
	}

	public void setUserToRegister(UserInputDto userToRegister) {
		this.userToRegister = userToRegister;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegisterRequest [userToRegister=");
		builder.append(userToRegister);
		builder.append("]");
		return builder.toString();
	}
}