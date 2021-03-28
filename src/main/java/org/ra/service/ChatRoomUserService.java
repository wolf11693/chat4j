package org.ra.service;

import java.util.HashSet;
import java.util.Set;

import org.ra.model.ChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomUserService {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserService.class);
	
	public Set<ChatModel> getAllChatByUsername(String username){
		LOG.info("getAllChat - START - username={}");
		
		Set<ChatModel> chatsRetrieved = new HashSet<>();
		
		LOG.info("getAllChat - END - retrieved n° {} chats", chatsRetrieved.size());
		return chatsRetrieved;
	}
}
