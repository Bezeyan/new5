package jm_social_project_micro.chat_service.controllers;

import jm_social_project_micro.chat_service.dto.ChatMessageDTO;
import jm_social_project_micro.chat_service.models.ChatMessage;
import jm_social_project_micro.chat_service.models.Conversation;
import jm_social_project_micro.chat_service.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ConversationService conversationService;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ConversationService conversationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.conversationService = conversationService;
    }

    @SubscribeMapping("/topic/public/{chatId}")
    public void processSubscribe(@Payload Conversation conversation) {
        conversationService.saveConversation(conversation);
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDTO messageDTO) {
        conversationService.updateConversation(messageDTO);
        simpMessagingTemplate.convertAndSend("/topic/public/" + messageDTO.getChatId(), messageDTO);
    }

    /*
    For tests connect to websocket
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
