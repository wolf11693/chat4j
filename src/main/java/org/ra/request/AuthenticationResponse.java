package org.ra.request;

public class AuthenticationResponse {

	private String username;
	private String jwtToken;
	private boolean authenticated;
	
	public AuthenticationResponse() {
		this(null, false, null);
	}

	public AuthenticationResponse(String username, boolean authenticated, String jwtToken) {
		super();
		this.username = username;
		this.jwtToken = jwtToken;
		this.authenticated = authenticated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticationResponse [username=");
		builder.append(username);
		builder.append(", jwtToken=");
		builder.append(jwtToken);
		builder.append(", authenticated=");
		builder.append(authenticated);
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", getJwtToken()=");
		builder.append(getJwtToken());
		builder.append(", isAuthenticated()=");
		builder.append(isAuthenticated());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}