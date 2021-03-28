package org.ra.service;

import java.util.HashSet;
import java.util.Set;

import org.ra.model.ChatModel;
import org.ra.model.ChatRoomUserModel;
import org.ra.model.UserModel;
import org.ra.repository.ChatRoomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomUserService {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserService.class);
	
	@Autowired
	@Qualifier(value = "dataChatRoomUserMongoRepository")
	private ChatRoomUserRepository chatRoomUserRepository;
	
	public Set<ChatModel> getAllChatByUsername(String username){
		LOG.info("getAllChat - START - username={}");
		
		Set<ChatModel> chatsRetrieved = new HashSet<>();
		
		LOG.info("getAllChat - END - retrieved n° {} chats", chatsRetrieved.size());
		return chatsRetrieved;
	}
	
	public ChatRoomUserModel createChatRoomUser(UserModel userOwnerChatRoom) {
		LOG.info("createChatRoomUser - START");
		
		ChatRoomUserModel chatRoomUserToCreate = new ChatRoomUserModel();
		chatRoomUserToCreate.setUserChats(new HashSet<>());
		chatRoomUserToCreate.setUserOwner(userOwnerChatRoom);
		
		ChatRoomUserModel chatUserRoomCreated = this.save(chatRoomUserToCreate);
		
		LOG.info("createChatRoomUser - END ");
		return chatUserRoomCreated;
	}
	
	public ChatRoomUserModel save(ChatRoomUserModel chatRoomUserToSave) {
		LOG.info("save - START - chatRoomUserModel={}");
		
		ChatRoomUserModel chatRoomUserSaved = this.chatRoomUserRepository.save(chatRoomUserToSave);
		
		LOG.info("save - END - id={}", chatRoomUserSaved);
		return chatRoomUserSaved;
	}
	
	
}
