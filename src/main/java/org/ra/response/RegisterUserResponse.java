package org.ra.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserResponse {

	@JsonProperty(value = "idUser")
	private String idUserRegistered;
	
	public RegisterUserResponse() {
		super();
	}

	public String getIdUserRegistered() {
		return idUserRegistered;
	}

	public void setIdUserRegistered(String idUserRegistered) {
		this.idUserRegistered = idUserRegistered;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegisterUserResponse [idUserRegistered=");
		builder.append(idUserRegistered);
		builder.append("]");
		return builder.toString();
	}
}