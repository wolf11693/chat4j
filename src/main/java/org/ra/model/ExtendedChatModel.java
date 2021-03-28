package org.ra.model;

public class ExtendedChatModel {

	private String idChatUserModel;
	private ChatModel chatModel;
	
	public ExtendedChatModel() {
		super();
	}

	public String getIdChatUserModel() {
		return idChatUserModel;
	}

	public ExtendedChatModel setIdChatUserModel(String idChatUserModel) {
		this.idChatUserModel = idChatUserModel;
		return this;
	}

	public ChatModel getChatModel() {
		return chatModel;
	}

	public ExtendedChatModel setChatModel(ChatModel chatModel) {
		this.chatModel = chatModel;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtendedChatModel [idChatUserModel=");
		builder.append(idChatUserModel);
		builder.append(", chatModel=");
		builder.append(chatModel);
		builder.append("]");
		return builder.toString();
	}
	
}