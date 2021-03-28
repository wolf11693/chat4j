package org.ra.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * This model rapresents the chat between 2 users.
 *  userOwnerChat is user who created a chat 
 *  otherUserChat: user who want speak 
 * 
 * @author Roberto
 * 
 * */
public class ChatModel {

	private String id;
	private String chatName;
	private Long creationDate;
	private UserModel userOwnerChat;
	private UserModel otherUserChat;
	private List<MessageModel> messages = new ArrayList<>();
	
	public ChatModel() {
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

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatModel [id=");
		builder.append(id);
		builder.append(", chatName=");
		builder.append(chatName);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", userOwnerChat=");
		builder.append(userOwnerChat);
		builder.append(", otherUserChat=");
		builder.append(otherUserChat);
		builder.append(", messages=");
		builder.append(messages);
		builder.append("]");
		return builder.toString();
	}

}