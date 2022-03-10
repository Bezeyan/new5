package jm_social_project_micro.chat_service.service;

import jm_social_project_micro.chat_service.models.ChatMessage;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
}
