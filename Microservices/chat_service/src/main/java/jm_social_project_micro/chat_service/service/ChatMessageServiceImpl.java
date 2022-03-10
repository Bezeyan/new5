package jm_social_project_micro.chat_service.service;

import jm_social_project_micro.chat_service.models.ChatMessage;
import jm_social_project_micro.chat_service.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setTimestamp(new Date());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}
