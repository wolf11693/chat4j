package org.ra.model;

public enum MongoCollectionEnum {

	CHAT_ROOM_COLLECTION("chatRoom"),
	USER_COLLECTION("user");
	
	private String value;
	
	MongoCollectionEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
