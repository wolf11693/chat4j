package org.ra.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class SessionManager {

	private final String USERNAME_USERS_IN_SESSION = "usernameUserList";

	private List<String> usernameUserInSession;

	private static HttpSession session;
	
	private static SessionManager _instance;
	
	private SessionManager(HttpSession session) {
		super();
		SessionManager.session = session;
		if(session.getAttribute(USERNAME_USERS_IN_SESSION) == null) {
			this.usernameUserInSession = new ArrayList<>();
			session.setAttribute(USERNAME_USERS_IN_SESSION, new ArrayList<String>());
		}
	}
	
	public static synchronized SessionManager createSessionManager(HttpSession session) {
		if(_instance == null) {
			_instance = new SessionManager(session);
		}
		
		return _instance;
	}
	
	public List<String> getUsernameUserInSession() {
		return usernameUserInSession;
	}

	public void addUsernameUserToSession(String username) {
		if(!this.usernameUserInSession.contains(username)) {
			this.usernameUserInSession.add(username);
		}
	}
	
}
