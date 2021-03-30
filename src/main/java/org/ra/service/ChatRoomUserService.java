package org.ra.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.ra.model.ChatRoomUserModel;
import org.ra.model.UserModel;
import org.ra.repository.ChatRoomUserRepositoryImpl;
import org.ra.repository.datamongo.ChatRoomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomUserService {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserService.class);
	
	@Autowired
	private ChatRoomUserRepository chatRoomUserRepository;
	
	public Set<ChatRoomUserModel> getAllChatRoom(){
		LOG.info("getAllChatRoom - START");

		List<ChatRoomUserModel> chatRoomList = this.chatRoomUserRepository.findAll();
		if(chatRoomList == null) {
			chatRoomList = new ArrayList<>();
		}
		Set<ChatRoomUserModel> chatRoomsRetrieved = new HashSet<>(chatRoomList);
		
		LOG.info("getAllChatRoom - END - retrieved n° {} chatRooms", chatRoomsRetrieved.size());
		return chatRoomsRetrieved;
	}
	
	public Set<ChatRoomUserModel> getChatRoomByUsername(String username){
		LOG.info("getChatRoomByUsername - START - username={}", username);
		
		Set<ChatRoomUserModel> chatRoomFiltered =  this.getAllChatRoom()
															.stream()
															.filter(chatRoom -> chatRoom.getUserOwner().getUsername().equals(username))
															.collect(Collectors.toSet());
		
		LOG.info("getChatRoomByUsername - END - retrieved n° {} chatRooms", chatRoomFiltered.size());
		return chatRoomFiltered;
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
