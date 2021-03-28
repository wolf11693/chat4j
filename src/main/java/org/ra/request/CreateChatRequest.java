package org.ra.request;

import org.ra.model.UserModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateChatRequest {

	@JsonProperty(value = "chatName")
	private String chatName;
	
	@JsonProperty(value = "idUserWantCreateChat")
	private UserModel idUserOwner;
	
	@JsonProperty(value = "idRecipientUser")
	private UserModel idOtherUser;
	
	public CreateChatRequest() {
		super();
	}

	public String getChatName() {
		return chatName;
	}
	
	public CreateChatRequest setChatName(String chatName) {
		this.chatName = chatName;
		return this;
	}

	public UserModel getIdUserOwner() {
		return idUserOwner;
	}

	public void setIdUserOwner(UserModel idUserOwner) {
		this.idUserOwner = idUserOwner;
	}

	public UserModel getIdOtherUser() {
		return idOtherUser;
	}

	public void setIdOtherUser(UserModel idOtherUser) {
		this.idOtherUser = idOtherUser;
	}

	public void setIdOtherUserChat(UserModel idOtherUser) {
		this.idOtherUser = idOtherUser;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateChatRequest [chatName=");
		builder.append(chatName);
		builder.append(", idUserOwner=");
		builder.append(idUserOwner);
		builder.append(", idOtherUser=");
		builder.append(idOtherUser);
		builder.append("]");
		return builder.toString();
	}
}