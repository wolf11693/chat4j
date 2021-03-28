package org.ra.model;

import org.ra.util.JwtUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user")
public class UserModel {

	@Id
	@Field(value = "id")
	private String id;

	@Field(value = "username")
	private String username;

	@Field(value = "firstname")
	private String firstname;

	@Field(value = "lastname")
	private String lastname;

	@Field(value = "email")
	private String email;

	@Field(value = "password")
	private String password;

	@Field(value = "image")
	private String image;

	public UserModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		builder.append("UserModel [id=");
		builder.append(id);
		builder.append(", username=");
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

	public JwtUser convertToJwtUser() {
		JwtUser jwtUser = new JwtUser();

		return jwtUser;
	}
}