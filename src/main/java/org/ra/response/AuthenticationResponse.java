package org.ra.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

	@JsonProperty("token")
	private String jwtToken;
	
	public AuthenticationResponse() {
		super();
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticationResponse [jwtToken=");
		builder.append(jwtToken);
		builder.append("]");
		return builder.toString();
	}
}
