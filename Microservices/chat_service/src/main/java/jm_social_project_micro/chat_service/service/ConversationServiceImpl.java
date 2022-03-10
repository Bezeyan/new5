package jm_social_project_micro.chat_service.service;

import jm_social_project_micro.chat_service.dto.ChatMessageDTO;
import jm_social_project_micro.chat_service.models.ChatMessage;
import jm_social_project_micro.chat_service.models.Conversation;
import jm_social_project_micro.chat_service.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    @Transactional
    public void updateConversation(ChatMessageDTO messageDTO) {
        Conversation conversation = conversationRepository
                .findConversationByChatId(messageDTO.getChatId());
        conversation.addParticipant(messageDTO.getSenderId());

        ChatMessage message = ChatMessage.builder()
                .timestamp(new Date())
                .senderId(messageDTO.getSenderId())
                .content(messageDTO.getContent())
                .build();
        conversation.addMessage(message);
        conversationRepository.save(conversation);
    }

    @Override
    @Transactional
    public void saveConversation(Conversation conversation) {
        Conversation found = conversationRepository.findConversationByChatId(conversation.getChatId());
        if (found == null) {
            conversation.setTimestampCreated(new Date());
            conversation.setMessages(new HashSet<>());
            conversation.setParticipantsIds(new HashSet<>());
            conversationRepository.save(conversation);
        }
    }
}
