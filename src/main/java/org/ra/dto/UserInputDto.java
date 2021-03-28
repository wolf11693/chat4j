package org.ra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInputDto {

	@JsonProperty(value = "username")
	private String username;
	
	@JsonProperty(value = "firstname")
	private String firstname;
	
	@JsonProperty(value = "lastname")
	private String lastname;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;
	
	@JsonProperty(value = "image")
	private String image;
	
	public UserInputDto() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInputDto [username=");
		builder.append(username);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}
}