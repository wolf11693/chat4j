package org.ra.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

/** 
 * This model is a wrapper for all chats of particular user 
 * 
 * @author Roberto
 * 
 * */
@Document(value = "chatRoom")
public class ChatRoomUserModel {

	private String id;
	private UserModel userOwner;
	private Set<ChatModel> userChats = new HashSet<>();

	public ChatRoomUserModel() {
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

	public Set<ChatModel> getUserChats() {
		return userChats;
	}

	public void setUserChats(Set<ChatModel> userChats) {
		this.userChats = userChats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		ChatRoomUserModel other = (ChatRoomUserModel) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if ( !id.equals(other.id) ) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatRoomUserModel [id=");
		builder.append(id);
		builder.append(", userOwner=");
		builder.append(userOwner);
		builder.append(", userChats=");
		builder.append(userChats);
		builder.append("]");
		return builder.toString();
	}
}