package org.ra.listner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.ra.manager.SessionManager;
import org.springframework.stereotype.Component;

@WebListener
@Component
public class MySessionListner implements HttpSessionListener {

	private SessionManager sessionManager;	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		this.sessionManager = SessionManager.createSessionManager(session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session destory");
	}
	
//	private void setActiveSessionCount(HttpSessionEvent sessionEvent) {
//		sessionEvent.getSession().getServletContext().setAttribute("activeSessions", "ciao");
//		System.out.println("Total Active Session: ");
//	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}
	
}