package jm_social_project_micro.chat_service.event;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ParticipantRepository {
    private final Map<String, String> activeSessions = new HashMap<>();

    public void add(String sessionId, String userId) {
        activeSessions.put(sessionId, userId);
    }

    public void remove(String sessionId) {
        activeSessions.remove(sessionId);
    }

    public Map<String, String> getActiveSessions() {
        return activeSessions;
    }
}
