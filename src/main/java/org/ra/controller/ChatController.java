package org.ra.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.ra.model.ChatModel;
import org.ra.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/chat")
	public ResponseEntity<Set<ChatModel>> getAll() {
		LOG.info("getAll - START");
		LOG.info(">> GET /chat");
		
		Set<ChatModel> chatsRetrieved = new HashSet<ChatModel>();
		
		LOG.info("<< [ n° {} items ]", chatsRetrieved.size());
		LOG.info("getAll - END");
		
		return new ResponseEntity<>(chatsRetrieved, HttpStatus.OK);
	}
	
	@GetMapping("/chat/userLogged")
	public ResponseEntity<Set<ChatModel>> getAllByUserLogged(Principal principal) {
		LOG.info("getAllByUserLogged - START - usernameUserLogged={}", principal != null? principal.getName(): null);
		LOG.info(">> GET /chat/userLogged");
		
		String username = principal.getName();
		Set<ChatModel> chatsRetrieved = new HashSet<ChatModel>();
		
		LOG.info("<< [ usernameUserLogged={}, n° {} items ]", username, chatsRetrieved.size());
		LOG.info("getAllByUserLogged - END");
		
		return new ResponseEntity<>(chatsRetrieved, HttpStatus.OK);
	}
	
}
