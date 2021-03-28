package org.ra.adapter;

import java.util.HashSet;
import java.util.Set;

import org.ra.dto.ChatOutputDto;
import org.ra.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class ChatAdapter {

	public ChatOutputDto adaptChatModel(ChatModel chatModel) {
		ChatOutputDto chatOutputDto = null;

		if(chatModel == null) {
			return chatOutputDto;
		}
		
		chatOutputDto = new ChatOutputDto();
		chatOutputDto.setId(chatModel.getId());
		chatOutputDto.setChatName(chatModel.getChatName());
		chatOutputDto.setCreationDate(chatModel.getCreationDate());
		chatOutputDto.setUserOwnerChat(chatModel.getUserOwnerChat());
		chatOutputDto.setOtherUserChat(chatModel.getOtherUserChat());
		
		return chatOutputDto;
	}
	
	public Set<ChatOutputDto> adaptChatsModel(Set<ChatModel> chatsModel){
		Set<ChatOutputDto> chatsOutputDto = new HashSet<>();

		if(chatsModel.isEmpty()) {
			return chatsOutputDto;
		}
		
		chatsModel.stream()
				  .forEach( chatModel -> chatsOutputDto.add( this.adaptChatModel(chatModel) ));
		
		return chatsOutputDto;
	}
}
