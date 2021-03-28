package org.ra.model;

/**
 * This model rapresents a chat
 * with relative idChatRoom where is cointained it
 * */
public class ExtendedChatModel {

	private String idChatRoom;
	private ChatModel chatModel;
	
	public ExtendedChatModel() {
		super();
	}

	public String getIdChatRoom() {
		return idChatRoom;
	}

	public void setIdChatRoom(String idChatRoom) {
		this.idChatRoom = idChatRoom;
	}

	public ChatModel getChatModel() {
		return chatModel;
	}

	public void setChatModel(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtendedChatModel [idChatRoom=");
		builder.append(idChatRoom);
		builder.append(", chatModel=");
		builder.append(chatModel);
		builder.append("]");
		return builder.toString();
	}
}