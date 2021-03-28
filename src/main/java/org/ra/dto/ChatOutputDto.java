package org.ra.dto;

import org.ra.model.UserModel;

public class ChatOutputDto {

	private String id;
	private String chatName;
	private Long creationDate;
	private UserModel userOwnerChat;
	private UserModel otherUserChat;
	
	public ChatOutputDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public UserModel getUserOwnerChat() {
		return userOwnerChat;
	}

	public void setUserOwnerChat(UserModel userOwnerChat) {
		this.userOwnerChat = userOwnerChat;
	}

	public UserModel getOtherUserChat() {
		return otherUserChat;
	}

	public void setOtherUserChat(UserModel otherUserChat) {
		this.otherUserChat = otherUserChat;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatOutputDto [id=");
		builder.append(id);
		builder.append(", chatName=");
		builder.append(chatName);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", userOwnerChat=");
		builder.append(userOwnerChat);
		builder.append(", otherUserChat=");
		builder.append(otherUserChat);
		builder.append("]");
		return builder.toString();
	}
}