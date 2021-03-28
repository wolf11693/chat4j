package org.ra.model;

import java.util.ArrayList;
import java.util.List;

public class MessageModel {

	private String id;
	private UserModel sender; 				// user who send a maessage
	private UserModel Recipient;			// user who receive a message
	private Long sendDate;
	private boolean isViewed;				
	private boolean deleted;
	private List<String> mediaAttachment = new ArrayList<>();
	
	public MessageModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserModel getSender() {
		return sender;
	}

	public void setSender(UserModel sender) {
		this.sender = sender;
	}

	public UserModel getRecipient() {
		return Recipient;
	}

	public void setRecipient(UserModel recipient) {
		Recipient = recipient;
	}

	public Long getSendDate() {
		return sendDate;
	}

	public void setSendDate(Long sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isViewed() {
		return isViewed;
	}

	public void setViewed(boolean isViewed) {
		this.isViewed = isViewed;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<String> getMediaAttachment() {
		return mediaAttachment;
	}

	public void setMediaAttachment(List<String> mediaAttachment) {
		this.mediaAttachment = mediaAttachment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageModel [id=");
		builder.append(id);
		builder.append(", sender=");
		builder.append(sender);
		builder.append(", Recipient=");
		builder.append(Recipient);
		builder.append(", sendDate=");
		builder.append(sendDate);
		builder.append(", isViewed=");
		builder.append(isViewed);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append(", mediaAttachment=");
		builder.append(mediaAttachment);
		builder.append("]");
		return builder.toString();
	}
}