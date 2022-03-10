package org.javamentor.social.notification_service.event_listener;

import org.javamentor.social.notification_service.model.UserOnline;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SessionManageRepository {

	private Map<String, UserOnline> activeSessions = new ConcurrentHashMap<>();

	public void addUserOnline(String sessionId, UserOnline userOnline) {
		activeSessions.put(sessionId, userOnline);
	}

	public UserOnline getUserOnline(String sessionId) {
		return activeSessions.get(sessionId);
	}

	public void removesDisconnect(String sessionId) {
		activeSessions.remove(sessionId);
	}

	public Map<String, UserOnline> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Map<String, UserOnline> activeSessions) {
		this.activeSessions = activeSessions;
	}
}
