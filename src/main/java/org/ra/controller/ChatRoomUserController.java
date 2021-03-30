package org.ra.controller;

import java.security.Principal;
import java.util.Set;

import org.ra.model.ChatRoomUserModel;
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
	
	@GetMapping("/chatroom")
	public ResponseEntity<Set<ChatRoomUserModel>> getAllChatRooms() {
		LOG.info("getAllChatRooms - START");
		LOG.info(">> GET /chatroom");
		
		Set<ChatRoomUserModel> chatRoomsRetrieved = this.chatRoomUserService.getAllChatRoom();
		
		LOG.info("<< [ n° {} items ]", chatRoomsRetrieved.size());
		LOG.info("getAllChatRooms - END");
		return new ResponseEntity<>(chatRoomsRetrieved, HttpStatus.OK);
	}
	
	@GetMapping("/chatroom/userLogged")
	public ResponseEntity<Set<ChatRoomUserModel>> getChatRoomUserLogged(Principal principal) {
		LOG.info("getChatRoomUserLogged - START - usernameUserLogged={}", principal.getName());
		LOG.info(">> GET /chatroom/userLogged");
		
		String username = principal.getName();
		Set<ChatRoomUserModel> chatRoomsRetrieved = this.chatRoomUserService.getChatRoomByUsername(username);
		
		LOG.info("<< [ usernameUserLogged={}, n° {} items ]", username, chatRoomsRetrieved.size());
		LOG.info("getAllChatRooms - END");
		return new ResponseEntity<>(chatRoomsRetrieved, HttpStatus.OK);
	}
	
}
