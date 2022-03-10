package org.javamentor.social.notification_service.event_listener;

import org.javamentor.social.notification_service.model.UserOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;


public class PresenceEventListener {

	@Autowired
	private SessionManageRepository sessionManageRepository;


	@EventListener
	public void handleSessionConnected(SessionConnectEvent event) {
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage());
		String username = headers.getLogin();
		UserOnline userOnline = new UserOnline(username);
		sessionManageRepository.addUserOnline(headers.getSessionId(), userOnline);
	}
	
	@EventListener
	public void handleSessionDisconnect(SessionDisconnectEvent event) {
		Optional.ofNullable(sessionManageRepository.getUserOnline(event.getSessionId()))
				.ifPresent(login -> sessionManageRepository.removesDisconnect(event.getSessionId()));
	}
}
