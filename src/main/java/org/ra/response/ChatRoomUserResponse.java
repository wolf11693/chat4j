package org.ra.response;

import java.util.Set;

import org.ra.dto.ChatOutputDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatRoomUserResponse {
	
	@JsonProperty(value = "username")
	private String username;
	
	@JsonProperty(value = "chats")
	private Set<ChatOutputDto> chats;
	
	public ChatRoomUserResponse() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<ChatOutputDto> getChats() {
		return chats;
	}

	public void setChats(Set<ChatOutputDto> chats) {
		this.chats = chats;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatRoomUserResponse [username=");
		builder.append(username);
		builder.append(", chats=");
		builder.append(chats);
		builder.append("]");
		return builder.toString();
	}
}