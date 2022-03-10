package jm_social_project_micro.chat_service.service;

import jm_social_project_micro.chat_service.dto.ChatMessageDTO;
import jm_social_project_micro.chat_service.models.Conversation;

public interface ConversationService {

    void updateConversation(ChatMessageDTO messageDTO);
    void saveConversation(Conversation conversation);
}
