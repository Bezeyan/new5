package org.javamentor.social.notification_service.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.javamentor.social.notification_service.event_listener.SessionManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.security.Principal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class MyChannelInterceptor implements ChannelInterceptor {

    private Map<String, String> userConnectionList;

    @Autowired
    SessionManageRepository repository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;
        String idUser = accessor.getFirstNativeHeader("id_user");
        StompCommand command = accessor.getCommand();
        if (command != null) {
            if (StompCommand.CONNECT.equals(command)) {
                Principal principal = accessor.getUser();
                accessor.setUser(principal);
                assert principal != null;
                userConnectionList.put(principal.getName(), idUser);
            }
            if (StompCommand.DISCONNECT.equals(command)) {
                userConnectionList.remove(idUser);
            }
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }
}
