package org.ra.controller;

import java.security.Principal;
import java.util.Set;

import org.ra.adapter.ChatAdapter;
import org.ra.model.ChatModel;
import org.ra.response.ChatRoomUserResponse;
import org.ra.service.ChatRoomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomUserController {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserController.class);
	
	@Autowired
	private ChatRoomUserService chatRoomUserService;
	
	@Autowired
	private ChatAdapter chatAdapter;
	
	@GetMapping("/chatroom")
	public ResponseEntity<ChatRoomUserResponse> getAllChat(Principal principal) {
		LOG.info("getAllChat - START - username={}", principal != null? principal.getName(): null);
		LOG.info(">> GET /chatroom");
		
		String username = principal.getName();
		Set<ChatModel> chatsRetrieved = this.chatRoomUserService.getAllChatByUsername(username);
		
		ChatRoomUserResponse chatRoomUserResponse = new ChatRoomUserResponse();
		chatRoomUserResponse.setUsername(username);
		chatRoomUserResponse.setChats(this.chatAdapter.adaptChatsModel(chatsRetrieved));
		
		LOG.info("<< [ username={}, numeberChats={} ]", username, chatsRetrieved.size());
		LOG.info("getAllChat - END");
		
		return new ResponseEntity<>(chatRoomUserResponse, HttpStatus.OK);
	}
	
}
