package org.ra.model;

public enum MongoCollectionEnum {

	CHAT_USER_COLLECTION("chatUserModel"),
	USER_COLLECTION("user");
	
	private String value;
	
	MongoCollectionEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
