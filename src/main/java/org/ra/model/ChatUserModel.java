package org.ra.model;

import java.util.HashSet;
import java.util.Set;

/** 
 * This model is a wrapper for all chats of particular user 
 * 
 * @author Roberto
 * 
 * */
public class ChatUserModel {

	private String id;
	private UserModel userOwner;
	private Set<ChatModel> userChat = new HashSet<>();

	public ChatUserModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserModel getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserModel userOwner) {
		this.userOwner = userOwner;
	}

	public Set<ChatModel> getUserChat() {
		return userChat;
	}

	public void setUserChat(Set<ChatModel> userChat) {
		this.userChat = userChat;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatUserModel [id=");
		builder.append(id);
		builder.append(", userOwner=");
		builder.append(userOwner);
		builder.append(", userChat=");
		builder.append(userChat);
		builder.append("]");
		return builder.toString();
	}

}